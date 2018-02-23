package com.spice.service.creation.response;
import lombok.Data;

@Data
public class VerifyResponse {
	private String userMngtPermission;
	private String serviceMngtPermission;
	private String userName;
	private String userId;
	private String userType;
	private String lastLoginDate;
	
}
