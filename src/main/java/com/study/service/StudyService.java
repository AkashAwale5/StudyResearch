package com.study.service;

import java.util.List;
import com.study.payloads.StudyDTO;

public interface StudyService {
	
	StudyDTO saveStudy(StudyDTO studydto);
	
	List<StudyDTO> getAllStudies();
	
	StudyDTO updateStudy(StudyDTO studydto,Integer studyId);

	StudyDTO getStudy(Integer studyId);
	
	void deleteStudy(Integer studyId);
	
	void updateStudyStatus(Integer studyId);
}
