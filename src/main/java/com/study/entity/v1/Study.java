package com.study.entity.v1;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
public class Study {

	private int id;

	@NotBlank(message = "Name may not be blank")
	@Size(min = 4)
	private String name;
	
	@Size(min = 15, max = 500)
	private String description;
	
	@NotBlank(message = "Priority may not be blank")
	private String priority;
	
	@Min(value=15, message="must be equal or greater than 15") 
	private int duration;
	
	private double version;
	
	private String createdBy;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp createdTS;

	private String updatedBy;

	@UpdateTimestamp
	@Column(insertable = false)
	private Timestamp updatedTS;
	
	private boolean isDeleted;
	
	
	private int status_id;

	private List<ContactDetails> contacts = new ArrayList<>();

}
