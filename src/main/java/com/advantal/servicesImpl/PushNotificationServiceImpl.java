package com.advantal.servicesImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advantal.model.PushNotification;
import com.advantal.repository.PushNotificationRepository;
import com.advantal.repository.UserRepository;
import com.advantal.requestPayload.PushNotificationRequestPayload;
import com.advantal.services.PushNotificationService;
import com.advantal.utils.Constant;

@Service
public class PushNotificationServiceImpl implements PushNotificationService {

	@Autowired
	PushNotificationRepository pushNotificationRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public Map<String, Object> saveNotifications(PushNotification notifications) {
		Map<String, Object> response = new HashMap<>();
		try {
			if (notifications != null && notifications.getUserId() != null) {
				PushNotification note = new PushNotification();
				note.setTitleName(notifications.getTitleName());
				note.setNotification(notifications.getNotification());
				note.setUserId(notifications.getUserId());
				note.setCreationDate(notifications.getCreationDate());
				note = pushNotificationRepository.save(note);
				response.put(Constant.RESPONSE_CODE, Constant.SUCCESS);
				response.put(Constant.MESSAGE, Constant.NOTIFICATION_SEND);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put(Constant.RESPONSE_CODE, Constant.NOT_AUTHORIZED);
			response.put(Constant.MESSAGE, Constant.ERROR_MESSAGE);
		}
		return response;
	}

	@Override
	public Map<String, Object> getNotificationsByUserId(Long userId) {
		Map<String, Object> response = new HashMap<>();
		List<PushNotificationRequestPayload> notificationList = new ArrayList<>();
		try {
			if (userId != null) {
				List<PushNotification> list = pushNotificationRepository.findByUserId(userId);
				for (PushNotification notification : list) {
					PushNotificationRequestPayload payloadNotificationPayload = new PushNotificationRequestPayload();
					payloadNotificationPayload.setNotification(notification.getNotification());
					payloadNotificationPayload.setTitleName(notification.getTitleName());
					payloadNotificationPayload.setUserId(notification.getUserId());
					payloadNotificationPayload.setCreationDate(notification.getCreationDate());
					payloadNotificationPayload.setNotificationId(notification.getNotificationId());
					notificationList.add(payloadNotificationPayload);
				}
				response.put(Constant.RESPONSE_CODE, Constant.SUCCESS);
				response.put(Constant.MESSAGE, Constant.NOTIFICATION_LIST);
				response.put(Constant.DATA, notificationList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put(Constant.RESPONSE_CODE, Constant.NOT_AUTHORIZED);
			response.put(Constant.MESSAGE, Constant.ERROR_MESSAGE);
		}
		return response;
	}

}
