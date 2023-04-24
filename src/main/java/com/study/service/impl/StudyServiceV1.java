package com.study.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.study.entity.v1.ContactDetails;
import com.study.entity.v1.Study;

@Service
public class StudyServiceV1 {

	private List<Study> list = new ArrayList<>();
	Date date = new Date();

	Random random = new Random();

	
	{
		List<ContactDetails> contacts = new ArrayList<>();
		contacts.add(new ContactDetails(11, "abc@gmail.com", "123456789", "Akash", (new Timestamp(date.getTime())),
				null, null, false));

		list.add(new Study(1, "H1N3", "H1N3", "LOW", 30, 1, "Akash", (new Timestamp(date.getTime())), null, null, false,
				1, contacts));
		list.add(new Study(2, "COVID", "COVID", "LOW", 30, 1, "Vaibhav", (new Timestamp(date.getTime())), null, null,
				false, 1, contacts));
	}

	// save data in list
	public Study saveStudy(Study study) {

		int randomId = random.nextInt(999);
		// setting default values
		study.setId(randomId);
		study.setVersion(0.1);
		study.setCreatedTS(new Timestamp(date.getTime()));
		study.setCreatedBy("Akash");
		study.setStatus_id(1);

		List<ContactDetails> contactList = study.getContacts();
		for (ContactDetails cd : contactList) {
			int contactId = random.nextInt(99);
			cd.setId(contactId);
			cd.setCreatedTS(new Timestamp(date.getTime()));
			cd.setCreatedBy("Akash");
		}

		study.setContacts(contactList);
		// adding study to list
		list.add(study);
		return study;
	}

	// get all data in list
	public List<Study> getStudies() {
		return list;
	}

	// update data in list
	public Study updateStudy(Study study) {
		int idx = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == study.getId()) {
				idx = i;
				break;
			}
		}
		Study st = new Study();
		st.setId(study.getId());
		st.setName(study.getName());
		st.setDescription(study.getDescription());
		st.setDuration(study.getDuration());
		st.setStatus_id(study.getStatus_id());
		st.setCreatedBy(study.getCreatedBy());
		st.setCreatedTS(study.getCreatedTS());
		st.setVersion(0.2);
		st.setUpdatedBy("updated");
		st.setUpdatedTS(new Timestamp(date.getTime()));

		List<ContactDetails> contactList = study.getContacts();
		for (ContactDetails cd : contactList) {
			cd.setUpdatedBy("updated");
			cd.setUpdatedTS(new Timestamp(date.getTime()));
		}
		st.setContacts(contactList);

		list.set(idx, st);

		return st;
	}

}
