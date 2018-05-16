package com.spice.service.creation.service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spice.service.creation.dao.NotificationDao;
import com.spice.service.creation.exception.GenericException;
import com.spice.service.creation.response.ResponseObj;
import com.spice.service.creation.response.ViewNotification;
import com.spice.service.creation.response.ViewNotificationResponse;


@Service
public class NotificationService {
	
	
	@Autowired
	private NotificationDao notificationDao;
	
	public ResponseObj clearNotification(String loginId,	String userId ) throws NumberFormatException, SQLException, GenericException {
		CallableStatement verifyResponses = notificationDao.clearNotification(loginId, userId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		return new ResponseObj(null , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	
	
	public ResponseObj updateReadNotification(String notificationId,	String loginId ) throws NumberFormatException, SQLException, GenericException {
		CallableStatement verifyResponses = notificationDao.updateReadNotification(notificationId, loginId);
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		return new ResponseObj(null , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
	
	
	public ResponseObj viewNotification(String loginId, String flag, String status ) throws NumberFormatException, SQLException, GenericException {
		CallableStatement verifyResponses = notificationDao.viewNotification(loginId, flag, status);
		List<ViewNotificationResponse> responses = new ArrayList<ViewNotificationResponse>();
		ResultSet set = verifyResponses.getResultSet();
		if(!"success".equalsIgnoreCase(verifyResponses.getString("OutStatus")))
			throw new GenericException(verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
		
		while(set.next()) {
		responses.add(new ViewNotificationResponse(set.getString("notification_id"), set.getString("user_id"), set.getString("notification_msg"), 
				set.getString("STATUS"), set.getString("read_date"), set.getString("insert_date")));
		}
		return new ResponseObj(new ViewNotification(verifyResponses.getString("outcount"), responses) , verifyResponses.getString("OutStatus"),verifyResponses.getString("OutDesc"), Integer.valueOf(verifyResponses.getString("OutResponseCode")));
	}
}
