package com.medibuddy.webapi.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medibuddy.webapi.entity.analysis.MedicalRecord;
import com.medibuddy.webapi.presentation.rest.v1.dto.record.MedicalRecordFieldModificationRequest;
import com.medibuddy.webapi.presentation.rest.v1.dto.record.MedicalRecordFieldValueResponse;
import com.medibuddy.webapi.repository.account.UserRepository;
import com.medibuddy.webapi.repository.ai.MlModelColumnRepository;
import com.medibuddy.webapi.repository.record.MedicalRecordRepository;

@Transactional
@Service
public class MedicalRecordService {

	@Autowired
	MedicalRecordRepository medicalRecordRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MlModelColumnRepository mlModelColumnRepository;

	public List<MedicalRecordFieldValueResponse> submitMedicalRecord(String username,
			List<MedicalRecordFieldModificationRequest> inputs) {
		var user = userRepository.findById(username);
		var medicalRecords = inputs.stream().map(request -> {
			var column = mlModelColumnRepository.findByName(request.name());
			var record = new MedicalRecord(user.get(), Instant.now(), column, request.value());
			record = medicalRecordRepository.save(record);
			return record;
		});
		var response = medicalRecords.map(record -> new MedicalRecordFieldValueResponse(
				record.getId(),
				record.getColumn().getName(),
				record.getValue(),
				record.getCreatedAt())).toList();
		return response;
	}

	public Page<MedicalRecordFieldValueResponse> getAllMedicalRecords(String username, Pageable pageable) {
		var records = medicalRecordRepository.findByUsername(username, pageable);
		var response = records.map(record -> new MedicalRecordFieldValueResponse(
				record.getId(),
				record.getColumn().getName(),
				record.getValue(),
				record.getCreatedAt()));
		return response;
	}

}
