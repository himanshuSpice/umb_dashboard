package com.spice.service.creation.exception;

public class ConstraintException extends Exception {

	final String message;
	final String requestId;
	
	
	public String getMessage() {
		return message;
	}

	public String getRequestId() {
		return requestId;
	}

	private static final long serialVersionUID = 1L;
	
	public ConstraintException(String message,String requestId){
		super(requestId);
		this.message=message;
		this.requestId=requestId;
		System.out.println("in exception class=="+message+","+requestId);
	}
}
