package com.spice.service.creation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spice.service.creation.request.ChangePasswordRequest;
import com.spice.service.creation.request.CreateUserAccount;
import com.spice.service.creation.request.LoginRequest;
import com.spice.service.creation.request.ResetPasswordRequest;
import com.spice.service.creation.request.UpdateUserAccountRequest;
import com.spice.service.creation.request.UpdateUserStatus;
import com.spice.service.creation.request.ViewUserRequest;
import com.spice.service.creation.response.ResponseObj;
import com.spice.service.creation.service.UserManagementService;

@RestController
@RequestMapping(value = "/userManagement")
public class UserManagementController {
	
	@Autowired
	private UserManagementService userManagementService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseObj verify(@Valid @RequestBody LoginRequest loginRequest) throws Exception{
		return userManagementService.verify(loginRequest);
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseObj changePassword(@Valid @RequestBody  ChangePasswordRequest  changePasswordRequest) throws Exception{
		return userManagementService.changePassword(changePasswordRequest);
	}
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ResponseObj createUserAccount(@Valid @RequestBody  CreateUserAccount  CreateUserAccount) throws Exception{
		return userManagementService.createUserAccount(CreateUserAccount);
	}
	
	@RequestMapping(value = "/updateUserStatus", method = RequestMethod.POST)
	public ResponseObj updateUserStatus(@RequestBody UpdateUserStatus  updateUserStatus) throws Exception{
		return userManagementService.updateUserStatus(updateUserStatus);
	}

	@RequestMapping(value = "/userDetail", method = RequestMethod.GET)
	public ResponseObj userDetail() throws Exception{
		return userManagementService.userDetails();
	}
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ResponseObj updateUserAccount(@Valid @RequestBody  UpdateUserAccountRequest  updateUserAccountRequest, @RequestHeader(value="userId") String userId) throws Exception{
		return userManagementService.updateUserAccount(updateUserAccountRequest, userId);
	}
	
	@RequestMapping(value = "/viewUser", method = RequestMethod.POST)
	public ResponseObj viewUser(@Valid @RequestBody  ViewUserRequest  viewUserRequest, @RequestHeader(required = false, value="userId") String userId ) throws Exception{
		return userManagementService.viewUser(viewUserRequest, userId);
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ResponseObj resetPassword(@RequestBody  ResetPasswordRequest  resetPasswordRequest, @RequestHeader(required = false, value="userId") String userId ) throws Exception{
		return userManagementService.resetPassword(resetPasswordRequest, userId);
	}
}
