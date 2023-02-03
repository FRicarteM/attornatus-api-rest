package com.attornatus.attornatus_assessment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 6900867693815210091L;

	public BadRequestException(String exception) {
		super(exception);		
	}
	
}
