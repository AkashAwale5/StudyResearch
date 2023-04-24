package com.study;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import com.study.entity.v2.ContactDetails;
import com.study.entity.v2.Status;
import com.study.entity.v2.Study;
import com.study.payloads.ContactDetailsDTO;
import com.study.payloads.StudyDTO;
import com.study.repository.ContactRepo;
import com.study.repository.StatusRepository;
import com.study.repository.StudyRepository;
import com.study.service.impl.StudyServiceImpl;

@DisplayName("StudyServiceImpl tests")
public class StudyServiceImplTest {

	@Mock
	private StudyRepository studyRepository;

	@Mock
	private StatusRepository statusRepository;

	@Mock
	private ContactRepo contactRepo;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private StudyServiceImpl studyService;

	@Test
	public void saveTest() {

		StudyDTO studyDto = new StudyDTO();
		studyDto.setName("COVID");
		studyDto.setDuration(30);

		Status st = new Status(1, "Active");

		when(statusRepository.findById(1)).thenReturn(Optional.of(st));
		
		List<ContactDetailsDTO> contactDetailsDTOs = new ArrayList<>();
		contactDetailsDTOs
				.add(new ContactDetailsDTO(1, "akash@gmail.com", "7709201759", null, null, null, null, false));

		List<ContactDetails> clist = contactDetailsDTOs.stream()
				.map(contact -> modelMapper.map(contact, ContactDetails.class)).collect(Collectors.toList());

		studyDto.setStatus(st);
		studyDto.setContacts(contactDetailsDTOs);

		Study study = new Study();
		study.setName(studyDto.getName());
		study.setDuration(studyDto.getDuration());
		study.setStatus(st);
		study.setContacts(clist);

		Study savedStudy = studyRepository.save(study);
		assertEquals(study.getName(), savedStudy.getName());
	}

	@Test
	void testGetAllStudies() {

		Status st = new Status(1, "Active");

		Study study1 = new Study();
		study1.setName("Study1");
		study1.setDuration(30);
		study1.setStatus(st);

		List<ContactDetails> contacts = new ArrayList<>();
		contacts.add(new ContactDetails(1, "abc@gmail.com", "1234567890", null, null, null, null, false, null));
		study1.setContacts(contacts);

		Study study2 = new Study();
		study2.setName("Study2");
		study2.setDuration(45);
		study2.setStatus(st);
		study2.setContacts(contacts);

		when(studyRepository.findAll()).thenReturn(Arrays.asList(study1, study2));

		StudyDTO studyDTO1 = new StudyDTO();
		studyDTO1.setName("Study1");
		studyDTO1.setDuration(30);

		StudyDTO studyDTO2 = new StudyDTO();
		studyDTO2.setName("Study2");
		studyDTO2.setDuration(45);

		when(modelMapper.map(study1, StudyDTO.class)).thenReturn(studyDTO1);
		when(modelMapper.map(study2, StudyDTO.class)).thenReturn(studyDTO2);

		List<StudyDTO> studyDTOs = studyService.getAllStudies();

		assertEquals(Arrays.asList(studyDTO1, studyDTO2), studyDTOs);
	}

}
