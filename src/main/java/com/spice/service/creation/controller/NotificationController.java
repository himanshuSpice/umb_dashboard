package com.spice.service.creation.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spice.service.creation.exception.GenericException;
import com.spice.service.creation.response.ResponseObj;
import com.spice.service.creation.service.NotificationService;

@RestController
@RequestMapping(value = "/notification")
public class NotificationController {
	
	
	@Autowired
	private NotificationService notificationService;
	
	
	@RequestMapping(value = "/clearNotification", method = RequestMethod.GET)
	public ResponseObj clearNotification(@RequestParam("loginId") String loginId, @RequestHeader(value="userId") String userId ) throws NumberFormatException, SQLException, GenericException {
		return notificationService.clearNotification(loginId, userId);
	}
	
	@RequestMapping(value = "/updateReadNotification", method = RequestMethod.GET)
	public ResponseObj updateReadNotification(@RequestParam("notificationId") String notificationId, @RequestParam(value="loginId") String loginId ) throws NumberFormatException, SQLException, GenericException {
		return notificationService.updateReadNotification(notificationId, loginId);
	}
	
	@RequestMapping(value = "/viewNotification", method = RequestMethod.GET)
	public ResponseObj viewNotification(@RequestParam(value="loginId") String loginId, @RequestParam(value="flag") String flag) throws NumberFormatException, SQLException, GenericException {
		return notificationService.viewNotification(loginId,flag );
	}
}
