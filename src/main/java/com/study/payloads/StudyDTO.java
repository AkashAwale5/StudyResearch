package com.study.payloads;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.study.entity.v2.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyDTO {

	private int id;

	@NotBlank(message = "Name may not be blank")
	@Size(min = 3)
	private String name;

	@Size(min = 15, max = 500)
	private String description;

	@NotBlank(message = "Priority may not be blank")
	private String priority;

	@Min(value = 15, message = "must be equal or greater than 15")
	private int duration;

	private double version;

	private String createdBy;

	private Timestamp createdTS;

	private String updatedBy;

	private Timestamp updatedTS;

	private boolean isDeleted;

	private List<ContactDetailsDTO> contacts = new ArrayList<ContactDetailsDTO>();

	private Status status;

}
