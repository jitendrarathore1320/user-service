package com.advantal.services;

import java.util.Map;

import com.advantal.model.PushNotification;

public interface PushNotificationService {

    Map<String,Object> saveNotifications(PushNotification notifications);
	
	Map<String,Object> getNotificationsByUserId(Long userId);
	
}
