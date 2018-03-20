package com.spice.service.creation.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ViewNotificationResponse{
	
	private String notificationId;
	private String userId;
	private String notificationMsg;
	private String status;
	private String readDate;
	private String insertDate;
}
