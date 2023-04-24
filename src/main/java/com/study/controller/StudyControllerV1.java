package com.study.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.entity.v1.Study;
import com.study.service.impl.StudyServiceV1;

@RestController
@RequestMapping("/api/v1/study")
public class StudyControllerV1 {
	
	@Autowired
	StudyServiceV1 studyService;
	
	@PostMapping
	public Study saveStudy(@Valid @RequestBody Study study)
	{
		Study savedStudy = studyService.saveStudy(study);
		return savedStudy;
	}
	
	@GetMapping
	public List<Study> getStudies()
	{
		return studyService.getStudies();
	}
	
	@PatchMapping
	public Study updateStudy(@RequestBody Study study)
	{
		return studyService.updateStudy(study);
	}

}
