package com.spice.service.creation.utility;

import org.springframework.context.MessageSource;
import java.util.Locale;

public enum ErrorCode {
	

	NO_RECORD_FOUND(125),	
	FAILURE(106),
	INTERNAL_SYSTEM_ERROR(150),
	SUCCESS(100),
	HEADER_REQUEST(104);
	

	private int status;
	private ErrorCode(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}
	public String getMessage(MessageSource messageSource, Object[] arg1,Locale arg2) {
		return messageSource.getMessage(String.valueOf(status), arg1, arg2);
	}

}
