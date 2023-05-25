package com.study.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.study.entity.v2.ContactDetails;
import com.study.entity.v2.Status;
import com.study.entity.v2.Study;
import com.study.exception.DuplicateRecordFoundException;
import com.study.exception.ResourceNotFoundException;
import com.study.payloads.StudyDTO;
import com.study.repository.StatusRepository;
import com.study.repository.StudyRepository;
import com.study.service.StudyService;

@Service
public class StudyServiceImpl implements StudyService {

	@Autowired
	private StudyRepository studyRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private CacheManager cacheManager;

	private static final DecimalFormat decformat = new DecimalFormat("0.00");

	// private Map<Integer, Study> cacheMap;
	private final Cache cache;

	public StudyServiceImpl(CacheManager cacheManager, StudyRepository studyRepository) {
		this.cache = cacheManager.getCache("myCache");
		this.studyRepository = studyRepository;
		// cacheMap = new HashMap<>();
	}

	// use Map to store cache Map of Map-------
	// @Cacheable(value = "myCustomCache", key = "#id")
	public Map<Integer, Map<String, String>> getDataById(Integer id) {
		// database call
		Study study = studyRepository.findById(id).orElse(null);
		// Convert the entity to a map
		Map<Integer, Map<String, String>> data = new HashMap<>();
		Map<String, String> rowData = new HashMap<>();
		rowData.put("Name", study.getName());
		rowData.put("CreatedBy", study.getCreatedBy());
		data.put(id, rowData);

		Map<Integer, Map<String, String>> cachedData = cache.get(id, Map.class);

		if (cachedData != null) {
			return cachedData;
		} else {
			cache.put(id, data);
			return data;
		}
	}

	// using cacheEvict for updating existing one
	@Override
	// @CacheEvict(value = "myCache", key = "'all'", allEntries = true)
	public StudyDTO saveStudy(StudyDTO studydto) {
		Study study = this.dtoToStudy(studydto);

		Status status = statusRepository.findById(1)
				.orElseThrow((() -> new ResourceNotFoundException("Status", " id ", 1)));
		// --------duplicate checking
		boolean findByName = studyRepository.findByName(study.getName()).isPresent();
		if (findByName == true) {
			throw new DuplicateRecordFoundException("Study", "name", study.getName());
		}
		// setting default values to study
		study.setCreatedBy("Akash");
		study.setVersion(0.1);
		study.setStatus(status);
		study.setIsDeleted(false);
		// setting values to contact
		List<ContactDetails> contacts = study.getContacts();
		for (int i = 0; i < contacts.size(); i++) {
			contacts.get(i).setCreatedBy("Akash");
			contacts.get(i).setStudy(study);
		}
		study.setContacts(contacts);
		Study savedStudy = this.studyRepository.save(study);
		return this.studytodto(savedStudy);
	}

	@Override
	// @Cacheable(value = "myCache", key = "'all'")
	public List<StudyDTO> getAllStudies() {
		List<Study> studies = studyRepository.findAll();
		List<Study> allStudies = new ArrayList<>();
		for (Study st : studies) {
			if (st.getIsDeleted() == false) {
				allStudies.add(st);
			}
		}
		List<StudyDTO> studylist = allStudies.stream().map(study -> this.studytodto(study))
				.collect(Collectors.toList());
		return studylist;
	}

	@Override
	public StudyDTO updateStudy(StudyDTO studydto, Integer studyId) {
		Study study = this.dtoToStudy(studydto);

		Study savedStudy = studyRepository.findById(studyId)
				.orElseThrow((() -> new ResourceNotFoundException("Study", " id ", studyId)));
		// ---------------duplicate checking------------
		boolean findByName = studyRepository.findByName(study.getName()).isPresent();

		if (findByName == true && savedStudy.getName().equals(study.getName()) || findByName == false) {
		} else {
			throw new DuplicateRecordFoundException("Study", "name", study.getName());
		}

		savedStudy.setName(study.getName());
		savedStudy.setDuration(study.getDuration());
		savedStudy.setStatus(study.getStatus());
		savedStudy.setDescription(study.getDescription());
		savedStudy.setPriority(study.getPriority());
		savedStudy.setUpdatedBy("vaibhav");

		String str = decformat.format(savedStudy.getVersion() + 0.1);
		double str1 = Double.parseDouble(str);
		savedStudy.setVersion(str1);

		List<ContactDetails> contacts = study.getContacts();
		for (int i = 0; i < contacts.size(); i++) {
			contacts.get(i).setUpdatedBy("Vaibhav");
			contacts.get(i).setStudy(study);
		}
		savedStudy.setContacts(contacts);
		return this.studytodto(studyRepository.save(savedStudy));
	}

	@Override
	// @Cacheable(value = "myCache", key = "#studyId")
	public StudyDTO getStudy(Integer studyId) {
		Study study = studyRepository.findById(studyId)
				.orElseThrow((() -> new ResourceNotFoundException("Study", " id ", studyId)));
		return this.studytodto(study);
	}

	@Override
	public void deleteStudy(Integer studyId) {
		Study study = studyRepository.findById(studyId)
				.orElseThrow((() -> new ResourceNotFoundException("Study", " id ", studyId)));

		String str = decformat.format(study.getVersion() + 0.1);
		double str1 = Double.parseDouble(str);
		study.setVersion(str1);

		study.setIsDeleted(true);
		study.setUpdatedBy("vaibhav");

		List<ContactDetails> contacts = study.getContacts();
		for (ContactDetails contactDetails : contacts) {
			contactDetails.setDeleted(true);
			contactDetails.setUpdatedBy("vaibhav");
		}
		studyRepository.save(study);
	}

	@Override
	public void updateStudyStatus(Integer studyId) {
		Study study = studyRepository.findById(studyId)
				.orElseThrow((() -> new ResourceNotFoundException("Study", " id ", studyId)));

		if (study.getStatus().getDescription().equals("InActive")) {
			Status status = statusRepository.findById(2)
					.orElseThrow((() -> new ResourceNotFoundException("Status", " id ", 2)));
			study.setStatus(status);

			String str = decformat.format(study.getVersion() + 0.1);
			double str1 = Double.parseDouble(str);
			study.setVersion(str1);
			study.setUpdatedBy("vaibhav");
		} else if (study.getStatus().getDescription().equals("Active")) {
			Status status = statusRepository.findById(3)
					.orElseThrow((() -> new ResourceNotFoundException("Status", " id ", 3)));
			study.setStatus(status);

			String str = decformat.format(study.getVersion() + 0.1);
			double str1 = Double.parseDouble(str);
			study.setVersion(str1);

			study.setUpdatedBy("vaibhav");
		}
		studyRepository.save(study);
	}

	// conversion of study to StudyDtos
	private Study dtoToStudy(StudyDTO studyDto) {
		Study study = this.modelmapper.map(studyDto, Study.class);
		return study;
	}

	// conversion of StudyDtos to study
	private StudyDTO studytodto(Study study) {
		StudyDTO studydto = this.modelmapper.map(study, StudyDTO.class);
		return studydto;
	}

	@Override
	public void validateName(StudyDTO studyDTO) {
		Study study = this.dtoToStudy(studyDTO);
		boolean findByName = studyRepository.findByName(study.getName()).isPresent();
		if (findByName == true) {
			throw new DuplicateRecordFoundException("Study", "name", study.getName());
		}
	}

	public List<StudyDTO> listOfIdsToSearch(String search) {

		Cache cache = cacheManager.getCache("myCustomCache");
		
		List<Study> data = new ArrayList<>();
		List<Integer> idsToFetch = new ArrayList<>();

		List<Integer> asList = Arrays.stream(search.split(",")).map(s -> Integer.valueOf(s))
				.collect(Collectors.toList());

		for (Integer id : asList) {
			ValueWrapper valueWrapper = cache.get(id);
			if (valueWrapper != null) {
				data.add((Study) valueWrapper.get());
			} else {
				idsToFetch.add(id);
			}
		}
		if (!idsToFetch.isEmpty()) {
			List<Study> findAllById = studyRepository.findAllById(idsToFetch);
			for (Study st : findAllById) {
				cache.put(st.getId(), st);
				data.add(st);
			}
		}
		// converting to study to studyDto
		List<StudyDTO> allStudies = data.stream().map(study -> this.studytodto(study)).collect(Collectors.toList());
		return allStudies;
	}

}
