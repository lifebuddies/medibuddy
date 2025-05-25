package com.medibuddy.webapi.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.medibuddy.webapi.ai.MlModelInputMapBuilder;
import com.medibuddy.webapi.config.init.MlModelBlueprintLoader;
import com.medibuddy.webapi.entity.analysis.Analysis;
import com.medibuddy.webapi.entity.analysis.AnalysisType;
import com.medibuddy.webapi.entity.analysis.Diagnosis;
import com.medibuddy.webapi.entity.analysis.Diagnosis.Criticality;
import com.medibuddy.webapi.presentation.rest.v1.dto.ai.MlModelInputColumnDto;
import com.medibuddy.webapi.presentation.rest.v1.dto.analysis.AnalysisRequest;
import com.medibuddy.webapi.presentation.rest.v1.dto.analysis.AnalysisResponse;
import com.medibuddy.webapi.presentation.rest.v1.dto.analysis.DiagnosisResponse;
import com.medibuddy.webapi.repository.analysis.*;
import com.medibuddy.webapi.repository.record.MedicalRecordRepository;
import com.medibuddy.webapi.entity.ai.MlModelBlueprint;

import ai.onnxruntime.OrtException;

@Validated
@Transactional
@Service
public class AnalysisService {

	@Autowired
	private MedicalRecordRepository medicalRecords;

	@Autowired
	private AnalysisRequestRepository analysisRequests;

	@Autowired
	private DiagnosisRepository diagnosisRepository;

	@Autowired
	private AnalysisTypeRepository analysisTypes;

	@Autowired
	private DiseaseAdviceRepository diseaseAdviceRepository;

	@Autowired
	private MedicalRecordService medicalRecordService;

	public List<String> getAnalysisTypes(Boolean availableOnly) {
		List<AnalysisType> types = analysisTypes.findAll();
		if (availableOnly) {
			types = types.stream()
					.filter(type -> type.getModel().getModelState() == MlModelBlueprint.State.RUNNING)
					.toList();
		}
		return types.stream()
				.map(AnalysisType::getName)
				.toList();
	}

	public List<MlModelInputColumnDto> getAnalysisInputDetails(String typeName, Boolean required) {
		return analysisTypes.findByName(typeName).getModel().getInputsColumns().stream()
				.map(input -> new MlModelInputColumnDto(input.getName(), input.getDataType(), input.getDescription()))
				.toList();
	}

	public DiagnosisResponse requestAnalysis(String username, String requestedAnalysisType, AnalysisRequest request)
			throws OrtException {
		var analysisType = analysisTypes.findByName(username);
		var analysisRequest = Analysis.builder().analysisType(analysisType).requestedAt(Instant.now()).build();
		analysisRequests.save(analysisRequest);

		medicalRecordService.submitMedicalRecord(username, request.inputs());

		List<String> inputColumns = analysisType.getModel().getInputsColumns().stream().map(model -> model.getName())
				.toList();
		var records = inputColumns
				.stream()
				.map(inputColumn -> medicalRecords
						.findTopByUserAndColumnNameOrderByUpdatedAtDesc(username, inputColumn)
						.get())
				.toList();
		var inputMap = MlModelInputMapBuilder.convertMedicalRecordsToSingltonMlModelInputMap(records);
		var result = MlModelBlueprintLoader.getModel(requestedAnalysisType).predict(inputMap).get(0);
		var prediction = new Diagnosis(analysisRequest, result,
				Boolean.parseBoolean(result) ? Criticality.HIGH : Criticality.LOW, Instant.now(), false);
		prediction = diagnosisRepository.save(prediction);

		var advices = diseaseAdviceRepository
				.findByCriticalityAndAnalysisType(prediction.getCriticality(), analysisType).stream()
				.map(advice -> advice.getMessage())
				.toList();
		return new DiagnosisResponse(prediction.getId(), prediction.getMlModelResult(), advices);
	}

	public AnalysisResponse getAnalysisRequestById(String username, UUID requestId) {
		var analysis = analysisRequests.findById(requestId).get();
		return new AnalysisResponse(analysis.getId(), analysis.getAnalysisType().getName(), analysis.getRequestedAt());
	}

	public DiagnosisResponse getDiagnosisByAnalysisId(String username, UUID requestId) {
		var analysisRequest = analysisRequests.findById(requestId);
		var diagnosis = diagnosisRepository.findByAnalysisRequest(analysisRequest.get()).get();
		var advices = diseaseAdviceRepository
				.findByCriticalityAndAnalysisType(diagnosis.getCriticality(), diagnosis.getAnalysis().getAnalysisType())
				.stream()
				.map(advice -> advice.getMessage())
				.toList();
		return new DiagnosisResponse(diagnosis.getId(), diagnosis.getMlModelResult(), advices);
	}

}
