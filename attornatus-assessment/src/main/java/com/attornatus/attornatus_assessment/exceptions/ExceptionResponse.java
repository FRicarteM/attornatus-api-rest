package com.attornatus.attornatus_assessment.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 8486068814491095598L;
	
	private String title;
	private String timestamp;
	private String message;
	private int status;
	private String details;

	
}
