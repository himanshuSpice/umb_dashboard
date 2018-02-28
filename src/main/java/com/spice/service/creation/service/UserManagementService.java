package com.spice.service.creation.service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spice.service.creation.dao.UserManagementDao;
import com.spice.service.creation.exception.GenericException;
import com.spice.service.creation.request.ChangePasswordRequest;
import com.spice.service.creation.request.CreateUserAccount;
import com.spice.service.creation.request.LoginRequest;
import com.spice.service.creation.request.ResetPasswordRequest;
import com.spice.service.creation.request.UpdateUserAccountRequest;
import com.spice.service.creation.request.UpdateUserStatus;
import com.spice.service.creation.request.ViewUserRequest;
import com.spice.service.creation.response.CreateUserResponse;
import com.spice.service.creation.response.ResponseObj;
import com.spice.service.creation.response.UpdateUserAccountResponse;
import com.spice.service.creation.response.UserDetailsResponse;
import com.spice.service.creation.response.VerifyResponse;
import com.spice.service.creation.response.ViewUserResponse;
import com.spice.service.creation.utility.UtilityData;

@Component
@Service
public class UserManagementService {
	

	@Autowired
	private UserManagementDao userManagementDao;

	
	public ResponseObj verify(LoginRequest loginRequest) throws Exception {
		UtilityData encryptData = new UtilityData();
		loginRequest.setPassword(encryptData.getMD5(loginRequest.getPassword()));
		CallableStatement verifyResponses = userManagementDao.verifyUser(loginRequest);
		VerifyResponse verifyResponse = new VerifyResponse();
		ResultSet set = verifyResponses.getResultSet();
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		while(set.next()) {
			verifyResponse.setUserName(loginRequest.getUserName());
			verifyResponse.setUserMngtPermission(set.getString("user_mngt_permission"));
			verifyResponse.setServiceMngtPermission(set.getString("service_mngt_permission"));
			verifyResponse.setUserId(set.getString("user_master_id"));
			verifyResponse.setLastLoginDate(set.getString("last_login_date"));
			verifyResponse.setUserType(set.getString("user_type"));
			}
		return new ResponseObj(verifyResponse, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		}
	
	
	public ResponseObj changePassword(ChangePasswordRequest changePasswordRequest )throws Exception {
		UtilityData encryptData = new UtilityData();
		changePasswordRequest.setPassword(encryptData.getMD5(changePasswordRequest.getPassword()));
		changePasswordRequest.setOldPassword(encryptData.getMD5(changePasswordRequest.getOldPassword()));
		CallableStatement verifyResponses = userManagementDao.changePassword(changePasswordRequest);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		return new ResponseObj(null , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}

	public ResponseObj createUserAccount( CreateUserAccount  CreateUserAccount)throws Exception {
		UtilityData encryptData = new UtilityData();
		CreateUserResponse createUserResponse = new CreateUserResponse();
		CreateUserAccount.setPassword(encryptData.getMD5(CreateUserAccount.getPassword()));
		if(CreateUserAccount.getUserIp()==null)
		CreateUserAccount.setUserIp(UtilityData.getMachineIp());
		CallableStatement verifyResponses = userManagementDao.createUserAccount(CreateUserAccount);
		ResultSet set = verifyResponses.getResultSet();
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));

		while(set.next()) {
			createUserResponse.setServiceId("OutUserMasterId");
		}
		return new ResponseObj(createUserResponse , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	
	public ResponseObj updateUserStatus(UpdateUserStatus  updateUserStatus)throws Exception {
		CallableStatement verifyResponses = userManagementDao.updateUserStatus(updateUserStatus);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		return new ResponseObj(null, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	
	public ResponseObj userDetails()throws Exception {
		List<UserDetailsResponse> response = userManagementDao.userDetails();
		if(response.isEmpty())
			throw new GenericException("Success", "No Record found", 125);
		
		return new ResponseObj(response);
		}
	public ResponseObj updateUserAccount( UpdateUserAccountRequest  updateUserAccountRequest, String userId)throws Exception {
		UtilityData encryptData = new UtilityData();
		updateUserAccountRequest.setPassword(encryptData.getMD5(updateUserAccountRequest.getPassword()));
		CallableStatement verifyResponses = userManagementDao.updateUserAccount(updateUserAccountRequest, userId);
		UpdateUserAccountResponse accountResponse = new UpdateUserAccountResponse();
		ResultSet result = verifyResponses.getResultSet();
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		while(result.next()) {
			accountResponse.setUserMasterId(result.getString("OutUserMasterId"));
			}		
		return new ResponseObj(accountResponse, verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	
	public ResponseObj viewUser(ViewUserRequest  viewUserRequest, String userId)throws Exception {
		List<ViewUserResponse> viewUserResponses = userManagementDao.viewUser(viewUserRequest, userId);
		if(viewUserResponses.isEmpty())
			throw new GenericException("Success", "No Record found", 125);
			return new ResponseObj(viewUserResponses);
	}
	


	public ResponseObj resetPassword(ResetPasswordRequest resetPasswordRequest, String userId) throws Exception {
		UtilityData encryptData = new UtilityData();
		resetPasswordRequest.setPassword(encryptData.getMD5(resetPasswordRequest.getPassword()));
		 CallableStatement response=userManagementDao.resetPasswordRequest(resetPasswordRequest,userId);
		 if(!"success".equalsIgnoreCase(response.getString("OutStatus")))
			 throw new GenericException(response.getString("OutStatus"),response.getString("OutDesc"), Integer.valueOf(response.getString("OutResponseCode")));
		
		 return new ResponseObj(null,response.getString("OutStatus"),response.getString("OutDesc"), Integer.valueOf(response.getString("OutResponseCode")));
	}
}
