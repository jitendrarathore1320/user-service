package com.advantal.services;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.advantal.requestPayload.SubscribeRequstPayload;
import com.advantal.requestPayload.SubscriptionRequestPayload;

public interface SubscriptionService {
	
	Map<String, Object> addSubscriptionPlan(SubscriptionRequestPayload subscriptionRequestPayload);

	Map<String, Object> getAllSubscriptionPlan();

	Map<String, Object> deleteSubscription(Long planId);

	Map<String, Object> subscribe(SubscribeRequstPayload subscribeRequstPayload);

	Map<String, Object> checkPlanExpire(Long userId);

	Map<String, Object> blockUnblockPlan(Long planId, Short status);

	Map<String, Object> updateProfileImage(MultipartFile image);

	Map<String, Object> getProductList();

	Map<String, Object> getPlanTypeList();

}