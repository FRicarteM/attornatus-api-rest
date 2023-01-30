package com.attornatus.attornatus_assessment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5045565922879097667L;

	public NotFoundException(String exception) {
		super(exception);		
	}
	
}
