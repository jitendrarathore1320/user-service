package com.advantal.services;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.advantal.model.User;
import com.advantal.requestPayload.UserLogsRequest;
import com.advantal.requestPayload.UserProfileRequestPayload;
import com.advantal.requestPayload.UserRequestPage;
import com.advantal.requestPayload.UserRequestPayload;
import com.advantal.requestPayload.VerifyRequestPayload;

public interface UserServices {

	Map<String, Object> getTncAndPolicy(Integer type);

	public Map<String, Object> getAllUser(UserRequestPage paginationPayLoad);

	public Map<String, Object> addUser(UserRequestPayload userPayload);

	public Map<String, Object> block_update(Long id, Short status);

	public Map<String, Object> savePolicy(String messsage, Integer type);

	public Map<String, Object> deleteUser(Long id);

	public Map<String, Object> userVerify(VerifyRequestPayload payload);

	Map<String, Object> updateProfileImage(MultipartFile profile_image, Long userId);

	Map<String, Object> updateProfile(UserProfileRequestPayload profileRequestPayload);

	Map<String, Object> getProfileById(Long id);

	Map<String, Object> getUserCount();

	Map<String, Object> getUserGraph(String type);

	List<User> getAllUserDetails(@Valid UserRequestPage userRequestPage);

	Map<String, Object> getAllUserLogs(@Valid UserLogsRequest paginationPayLoad);
}
