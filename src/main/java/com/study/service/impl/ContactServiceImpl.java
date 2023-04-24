package com.study.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.entity.v2.ContactDetails;
import com.study.payloads.ContactDetailsDTO;
import com.study.repository.ContactRepo;
import com.study.service.ContactService;


@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepo contactRepo;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public ContactDetailsDTO saveContact(ContactDetailsDTO contactDetailsdto) {
		ContactDetails contact = dtoToContact(contactDetailsdto);
		return contactToDto(contactRepo.save(contact));
	}

	@Override
	public List<ContactDetailsDTO> getContacts() {
		List<ContactDetails> contacts = contactRepo.findAll();
		List<ContactDetailsDTO> contactlist = contacts.stream().map(contact->contactToDto(contact)).collect(Collectors.toList());
		return contactlist;
	}
   
	//conversion of dto
	private ContactDetails dtoToContact(ContactDetailsDTO contactDetailsDTO)
	{
		return modelmapper.map(contactDetailsDTO, ContactDetails.class);
	}
	
	private ContactDetailsDTO contactToDto(ContactDetails contactDetails)
	{
		return modelmapper.map(contactDetails, ContactDetailsDTO.class);
	}
}
