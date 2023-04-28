package com.study.service;

import java.util.List;
import com.study.payloads.ContactDetailsDTO;

public interface ContactService {
	
	ContactDetailsDTO saveContact(ContactDetailsDTO contactDetailsdto);
	
	List<ContactDetailsDTO> getContacts();

}
