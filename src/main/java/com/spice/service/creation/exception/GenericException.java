package com.spice.service.creation.exception;

import lombok.Getter;

@Getter
public class GenericException extends Exception {

	final String status;
	final String errorDescription;
	final int code;

	private static final long serialVersionUID = 1L;

	public GenericException(String status,  String description, int code) {
		super();
		this.status = status;
		this.errorDescription = description;
		this.code = code;
	}

}
