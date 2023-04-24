package com.study.payloads;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetailsDTO {

	private int id;

	private String email;

	private String contact;

	private String createdBy;

	private Timestamp createdTS;

	private String updatedBy;

	private Timestamp updatedTS;

	private boolean isDeleted;
	
	

}
