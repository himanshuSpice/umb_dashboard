package com.spice.service.creation.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ViewNotification {
	private String count;
	private List<ViewNotificationResponse> viewNotificationResponse;
}
