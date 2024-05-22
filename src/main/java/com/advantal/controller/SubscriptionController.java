package com.advantal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.advantal.requestPayload.SubscribeRequstPayload;
import com.advantal.requestPayload.SubscriptionRequestPayload;
import com.advantal.services.SubscriptionService;
import com.advantal.utils.Constant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;
	
	@GetMapping(value = "/get_Product_list")
	public ResponseEntity<Map<String, Object>> getProductList() {
		return new ResponseEntity<Map<String, Object>>(
				subscriptionService.getProductList(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/get_plan_type_list")
	public ResponseEntity<Map<String, Object>> getPlanTypeList() {
		return new ResponseEntity<Map<String, Object>>(
				subscriptionService.getPlanTypeList(), HttpStatus.OK);
	}


	@PostMapping(value = "/user_plan")
	public ResponseEntity<Map<String, Object>> addSubscriptionPlan(
			@RequestBody @Valid SubscriptionRequestPayload subscriptionRequestPayload) {
		return new ResponseEntity<Map<String, Object>>(
				subscriptionService.addSubscriptionPlan(subscriptionRequestPayload), HttpStatus.OK);
	}

	@GetMapping("/plans")
	public ResponseEntity<Map<String, Object>> getAllSubscriptionPlan() {
		return new ResponseEntity<Map<String, Object>>(subscriptionService.getAllSubscriptionPlan(), HttpStatus.OK);
	}

	@PostMapping("/delete_plan")
	public ResponseEntity<Map<String, Object>> deleteSubscriptionPlan(@RequestParam(required = true) Long planId) {
		return new ResponseEntity<Map<String, Object>>(subscriptionService.deleteSubscription(planId), HttpStatus.OK);

	}

	@PostMapping("/subscribe")
	public ResponseEntity<Map<String, Object>> subscribe(
			@RequestBody @Valid SubscribeRequstPayload subscribeRequstPayload) {
		return new ResponseEntity<Map<String, Object>>(subscriptionService.subscribe(subscribeRequstPayload),
				HttpStatus.OK);
	}

	@GetMapping("/plan_expired")
	public ResponseEntity<Map<String, Object>> checkPlanExpire(@RequestParam(required = true) Long userId) {
		return new ResponseEntity<Map<String, Object>>(subscriptionService.checkPlanExpire(userId), HttpStatus.OK);
	}

	@PostMapping("/upload_image")
	public Map<String, Object> uplaodImage(@RequestPart(value = "images", required = false) MultipartFile images) {
		Map<String, Object> map = new HashMap<>();
		if (images != null) {
			return subscriptionService.updateProfileImage(images);
		} else {
			map.put(Constant.RESPONSE_CODE, Constant.BAD_REQUEST);
			map.put(Constant.MESSAGE, Constant.BAD_REQUEST_MESSAGE);
			log.info("Bad request! status - {}", Constant.BAD_REQUEST);
			return map;
		}
	}

	@PostMapping("/block_unblock_plan")
	public ResponseEntity<Map<String, Object>> blockUnlockPlan(@RequestParam(required = true) Long planId,
			@RequestParam(required = true) Short status) {
		return new ResponseEntity<Map<String, Object>>(subscriptionService.blockUnblockPlan(planId, status),
				HttpStatus.OK);
	}
}