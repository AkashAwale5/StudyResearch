package com.study;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.study.controller.StudyControllerV2;
import com.study.entity.v2.Status;
import com.study.payloads.ContactDetailsDTO;
import com.study.payloads.StudyDTO;
import com.study.service.impl.StudyServiceImpl;

@ExtendWith(MockitoExtension.class)
public class StudyControllerV2Test {

	@Mock
	private StudyServiceImpl studyService;

	@InjectMocks
	private StudyControllerV2 studyController;

	@Test
	public void testSaveStudy() {
		ContactDetailsDTO contactDto= new ContactDetailsDTO(1, "akash@gmail.com", "1234567890", null, null, null, null, false);
		List<ContactDetailsDTO> contactList= new ArrayList<>();
		contactList.add(contactDto);
		
		Status st = new Status(1, "Active");
		
		StudyDTO inputDTO = new StudyDTO();
		inputDTO.setName("test");
		inputDTO.setDescription("test description");
		inputDTO.setContacts(contactList);
		inputDTO.setStatus(st);
		

		StudyDTO savedDTO = new StudyDTO();
		savedDTO.setId(1);
		savedDTO.setName(inputDTO.getName());
		savedDTO.setDescription(inputDTO.getDescription());
		
		savedDTO.setContacts(inputDTO.getContacts());

		when(studyService.saveStudy(inputDTO)).thenReturn(savedDTO);

		StudyDTO resultDTO = studyController.saveStudy(inputDTO);
	
		assertEquals(savedDTO, resultDTO);
	}
	
//	@Test
//    public void testGetStudies() {
//		
//		ContactDetailsDTO contactDto= new ContactDetailsDTO(1, "akash@gmail.com", "1234567890", null, null, null, null, false);
//		List<ContactDetailsDTO> contactList= new ArrayList<>();
//		contactList.add(contactDto);
//		
//        List<StudyDTO> studyList = new ArrayList<>();
//        StudyDTO studyDTO = new StudyDTO();
//        studyDTO.setId(1);
//        studyDTO.setName("test");
//        studyDTO.setDescription("test description");
//        studyDTO.setContacts(contactList);
//        
//        studyList.add(studyDTO);
//
//        when(studyService.getAllStudies()).thenReturn(studyList);
//
//        List<StudyDTO> resultList = studyController.getStudies();
//
//        assertEquals(studyList, resultList);
//    }
	
	@Test
    public void testGetStudy() {
		
		ContactDetailsDTO contactDto= new ContactDetailsDTO(1, "akash@gmail.com", "1234567890", null, null, null, null, false);
		List<ContactDetailsDTO> contactList= new ArrayList<>();
		contactList.add(contactDto);
		
		
        Integer studyId = 1;

        StudyDTO studyDTO = new StudyDTO();
        studyDTO.setId(studyId);
        studyDTO.setName("test");
        studyDTO.setDescription("test description");
        studyDTO.setContacts(contactList);

        when(studyService.getStudy(studyId)).thenReturn(studyDTO);

        StudyDTO resultDTO = studyController.getStudy(studyId);

        assertEquals(studyDTO, resultDTO);
    }
	
	@Test
    public void testUpdateStudy() {
        Integer studyId = 1;
        StudyDTO inputDTO = new StudyDTO();
        inputDTO.setId(studyId);
        inputDTO.setName("updated test");
        inputDTO.setDescription("updated test description");

        StudyDTO updatedDTO = new StudyDTO();
        updatedDTO.setId(studyId);
        updatedDTO.setName(inputDTO.getName());
        updatedDTO.setDescription(inputDTO.getDescription());

        when(studyService.updateStudy(inputDTO, studyId)).thenReturn(updatedDTO);

        StudyDTO resultDTO = studyController.updateStudy(inputDTO, studyId);

        assertEquals(updatedDTO, resultDTO);
    }

}
