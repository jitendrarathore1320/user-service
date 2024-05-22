package com.advantal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.advantal.requestPayload.UserLogsRequest;
import com.advantal.requestPayload.UserProfileRequestPayload;
import com.advantal.requestPayload.UserRequestPage;
import com.advantal.requestPayload.UserRequestPayload;
import com.advantal.requestPayload.VerifyRequestPayload;
import com.advantal.services.PushNotificationService;
import com.advantal.services.UserServices;
import com.advantal.utils.Constant;

//import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired 
	private UserServices userServices; 

	@Autowired
	PushNotificationService pushNotificationService;

	@PostMapping("/add_users")
	public Map<String, Object> addUser(@RequestBody @Valid UserRequestPayload userPayload) {
		Map<String, Object> response = new HashMap<>();
		if (userPayload != null) {
			response = userServices.addUser(userPayload);
		} else {
			response.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
			response.put(Constant.MESSAGE, Constant.BAD_REQUEST_MESSAGE);
		}
		return response;
	}

	
	
	@PostMapping("/getAllUsers")
	public Map<String, Object> getAllUser(@RequestBody UserRequestPage paginationPayLoad) {
		Map<String, Object> response = new HashMap<>();
		if (paginationPayLoad != null) {
			response = userServices.getAllUser(paginationPayLoad);
		} else {
			response.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
			response.put(Constant.MESSAGE, Constant.BAD_REQUEST_MESSAGE);
		}
		return response;
	}

	@DeleteMapping("/delete_user")
	public Map<String, Object> deleteUser(@RequestParam Long id) {
		return userServices.deleteUser(id);
	}

	@PostMapping("/block_unblock_user")
	public Map<String, Object> block_unlock(@RequestParam(required = true) Long id,
			@RequestParam(required = true) Short status) {
		Map<String, Object> response = new HashMap<>();
		if (id != null) {
			response = userServices.block_update(id, status);
		}
		return response;
	}

	@PostMapping(value = "/verify")
	public Map<String, Object> userVerify(@RequestBody @Valid VerifyRequestPayload payload) {
		return userServices.userVerify(payload);
	}

	@PostMapping("/term_condition_and_privacy_policy")
	public Map<String, Object> saveAmwalDocs(@RequestParam(required = true) String messsage,
			@RequestParam(required = true) Integer type) {
		return userServices.savePolicy(messsage, type);
	}

	@GetMapping(value = "/get_term_condition_and_privacy_policy")
	public Map<String, Object> getTncAndPolicy(@RequestParam(required = true) Integer type) {
		return userServices.getTncAndPolicy(type);
	}

	@PostMapping("/update_profile_image")
	public Map<String, Object> updateProfileImage(
			@RequestPart(value = "profile_image", required = false) MultipartFile profile_image,
			@RequestParam(required = true) Long userId) {
		Map<String, Object> map = new HashMap<>();
		if (profile_image != null) {
			return userServices.updateProfileImage(profile_image, userId);
		} else {
			map.put(Constant.RESPONSE_CODE, Constant.BAD_REQUEST);
			map.put(Constant.MESSAGE, Constant.BAD_REQUEST_MESSAGE);
			log.info("Bad request! status - {}", Constant.BAD_REQUEST);
			return map;
		}
	}

	@PostMapping("/update_profile")
	public Map<String, Object> updateProfile(@RequestBody UserProfileRequestPayload profileRequestPayload) {
		Map<String, Object> map = new HashMap<>();
		if (profileRequestPayload != null) {
			return userServices.updateProfile(profileRequestPayload);
		} else {
			map.put(Constant.RESPONSE_CODE, Constant.BAD_REQUEST);
			map.put(Constant.MESSAGE, Constant.BAD_REQUEST_MESSAGE);
			log.info("Bad request! status - {}", Constant.BAD_REQUEST);
			return map;
		}
	}

	@GetMapping(value = "/get_profile_by_id")
	public Map<String, Object> getProfileById(@RequestParam(required = true) Long userId) {
		Map<String, Object> response = new HashMap<>();
		if (userId != null) {
			response = userServices.getProfileById(userId);
		} else {
			response.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
			response.put(Constant.MESSAGE, Constant.BAD_REQUEST_MESSAGE);
		}
		return response;
	}

	@GetMapping(value = "/user_count")
	public Map<String, Object> getUserCount() {
		return userServices.getUserCount();
	}

	@GetMapping(value = "/user_graph")
	public Map<String, Object> getUserGraph(@RequestParam(required = true) String type) {
		return userServices.getUserGraph(type);
	}

	
	@PostMapping(value = "/get_user_logs")
	public Map<String, Object> getAllUserLogs(@RequestBody @Valid UserLogsRequest paginationPayLoad) {
		Map<String, Object> response = new HashMap<>();
		if (paginationPayLoad != null) {
			response = userServices.getAllUserLogs(paginationPayLoad);
		} else {
			response.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
			response.put(Constant.MESSAGE, Constant.BAD_REQUEST_MESSAGE);
		}
		return response;
	}
	
	/*-----------------notification------------------------*/
	/*
	 * @GetMapping(value = "/get_User_notifications") public Map<String, Object>
	 * getNotificationsUserId(@RequestParam(value = "UserId") Long UserId) { return
	 * pushNotificationService.getNotificationsByUserId(UserId); }
	 */

}