package com.study.entity.v2;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Email(message = "Email address is not valid ")
	private String email;
	
	@Size(min = 10, max = 10, message = "size cannot be less than or more than 10")
	private String contact;
	private String createdBy;
	
	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp createdTS;
	private String updatedBy;
	
	@UpdateTimestamp
	@Column(insertable = false)
	private Timestamp updatedTS;
	private boolean isDeleted;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "study_id", referencedColumnName = "id")
	private Study study;
}
