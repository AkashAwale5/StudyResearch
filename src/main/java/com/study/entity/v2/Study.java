package com.study.entity.v2;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "name" }))
public class Study {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;
	private String description;
	private String priority;
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
	
	private Boolean isDeleted;

	@JsonManagedReference
	@OneToMany(mappedBy="study",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<ContactDetails> contacts = new ArrayList<ContactDetails>();

	@OneToOne
	@JoinColumn(name = "status_id")
	private Status status;

}
