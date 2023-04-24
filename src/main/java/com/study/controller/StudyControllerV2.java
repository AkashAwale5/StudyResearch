package com.study.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.entity.v2.Study;
import com.study.payloads.StudyDTO;
import com.study.service.impl.StudyServiceImpl;

@RestController
@RequestMapping("/api/v2/study")
@CrossOrigin
public class StudyControllerV2 {

	@Autowired
	private StudyServiceImpl studyService;

	@PostMapping
	public StudyDTO saveStudy(@Valid @RequestBody StudyDTO studydto) {
		StudyDTO savedStudy = studyService.saveStudy(studydto);
		return savedStudy;
	}

	@GetMapping
	public List<StudyDTO> getStudies() {
		return studyService.getAllStudies();
	}

	@GetMapping("/{studyId}")
	public StudyDTO getStudy(@PathVariable Integer studyId) {
		return studyService.getStudy(studyId);
	}

	@PutMapping("/{studyId}")
	public StudyDTO updateStudy(@Valid @RequestBody StudyDTO study, @PathVariable Integer studyId) {
		return studyService.updateStudy(study, studyId);
	}

	@DeleteMapping("/{studyId}")
	public void deleteStudy(@PathVariable Integer studyId) {
		studyService.deleteStudy(studyId);
	}

	@PutMapping("/status/{studyId}")
	public void updateStatus(@PathVariable Integer studyId) {
		studyService.updateStudyStatus(studyId);

	}

}
