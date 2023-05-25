package com.study.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public List<StudyDTO> getStudies(@RequestParam(value = "search", required = false) String search) {
		
		if (StringUtils.isEmpty(search)) {
			return studyService.getAllStudies();//all
		}else {
			return studyService.listOfIdsToSearch(search);	//search
		}
		
	}

	@GetMapping("/{studyId}")
	public StudyDTO getStudy(@PathVariable Integer studyId) {
		return studyService.getStudy(studyId);
	}

	@PatchMapping("/{studyId}")
	public StudyDTO updateStudy(@Valid @RequestBody StudyDTO study, @PathVariable Integer studyId) {
		return studyService.updateStudy(study, studyId);
	}

	@DeleteMapping("/{studyId}")
	public void deleteStudy(@PathVariable Integer studyId) {
		studyService.deleteStudy(studyId);
	}

	@PatchMapping("/status/{studyId}")
	public void updateStatus(@PathVariable Integer studyId) {
		studyService.updateStudyStatus(studyId);

	}
	
	@GetMapping("/validate")
	public void validateArgument(@RequestBody StudyDTO studyDTO)
	{
		studyService.validateName(studyDTO);
	}

//	@GetMapping("/printCache/{id}")
//	public Map<Integer,Map<String,String>> printData(@PathVariable int id)
//	{
//		return studyService.getDataById(id);
//		//studyService.printCacheData();
//	}
	
}
