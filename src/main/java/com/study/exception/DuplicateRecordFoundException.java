package com.study.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicateRecordFoundException extends RuntimeException{

	String resourceName;
	String fieldName;
	String fieldvalue;
	public DuplicateRecordFoundException(String resourceName, String fieldName, String fieldvalue) {
		super(String.format("%s found duplicate entry with %s: %s", resourceName,fieldName,fieldvalue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldvalue = fieldvalue;
	}
		
}