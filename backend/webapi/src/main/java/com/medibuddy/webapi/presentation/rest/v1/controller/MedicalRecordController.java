package com.medibuddy.webapi.presentation.rest.v1.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medibuddy.webapi.presentation.rest.v1.dto.record.MedicalRecordInputDto;

@RestController
@RequestMapping("/api/v1/medical-records")
public class MedicalRecordController {

	@PostMapping
	public ResponseEntity<?> submitMedicalRecord(@RequestBody MedicalRecordInputDto entity) {
		// TODO: Implement the logic to submit a medical record
		return ResponseEntity.ok("Medical record submitted successfully!");
	}

	@GetMapping
	public ResponseEntity<?> getAllMedicalRecords(Pageable pageable) {
		// TODO: Implement the logic to retrieve a medical record by ID
		return ResponseEntity.ok("Medical record retrieved successfully!");
	}

	@GetMapping("/{recordId}")
	public ResponseEntity<?> getMedicalRecordById(@PathVariable String recordId) {
		// TODO: Implement the logic to retrieve a medical record by ID
		return ResponseEntity.ok("Medical record retrieved successfully!");
	}

}
