package com.attornatus.attornatus_assessment.exceptions;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 8486068814491095598L;
	
	@Schema(type = "string", example = "Name of Exception")
	private String title;
	@Schema(type = "string", example = "11 de jan. de 2023 18:00:52")
	private String timestamp;
	@Schema(type = "string", example = "Message of Exception")
	private String message;
	@Schema(type = "string", example = "Code of Exception")
	private int status;
	@Schema(type = "string", example = "Details of Exception")
	private String details;
	
}
