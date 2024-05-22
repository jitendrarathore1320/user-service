
package com.advantal.servicesImpl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.advantal.model.Otp;
import com.advantal.model.TncAndPolicy;
import com.advantal.model.User;
import com.advantal.model.UserLogs;
import com.advantal.repository.TncAndConditionRepository;
import com.advantal.repository.UserLogsRepository;
import com.advantal.repository.UserOtpRepository;
import com.advantal.repository.UserRepository;
import com.advantal.requestPayload.CountResponse;
import com.advantal.requestPayload.UserLogsRequest;
import com.advantal.requestPayload.UserProfileRequestPayload;
import com.advantal.requestPayload.UserRequestPage;
import com.advantal.requestPayload.UserRequestPayload;
import com.advantal.requestPayload.VerifyRequestPayload;
import com.advantal.responsePayload.MonthObject;
import com.advantal.responsePayload.UserGraphResponse;
import com.advantal.responsePayload.UserLogResponse;
import com.advantal.responsePayload.UserLogResponsePage;
import com.advantal.responsePayload.UserResponse;
import com.advantal.responsePayload.UserResponsePage;
import com.advantal.services.UserServices;
import com.advantal.utils.Constant;
import com.advantal.utils.FileUploader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserServices {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserOtpRepository userOtpRepository;

	@Autowired
	private UserLogsRepository userLogsRepository;

	@Autowired
	private UtilityServiceImpl utilityServiceImpl;
	
	@Autowired
	private TncAndConditionRepository tncAndConditionRepository;

	@Value("${spring.imageuploaddir}")
	private String imageUploadDir;

	@Value("${spring.imageurl}")
	private String imageUrl;

	@Override
	public Map<String, Object> getAllUser(UserRequestPage paginationPayLoad) {
		Map<String, Object> response = new HashMap<>();
		Pageable pageable = null;
		Page<User> page = null;
		List<User> userList = new ArrayList<>();
		List<UserResponse> userResonseList = new ArrayList<>();
		UserResponsePage userResponsePage = new UserResponsePage();
		try {
			if (paginationPayLoad.getPageSize() != 0) {
				if (!paginationPayLoad.getSortBy().isBlank()) {
					pageable = PageRequest.of(paginationPayLoad.getPageIndex(), paginationPayLoad.getPageSize(),
							Sort.by(paginationPayLoad.getSortBy()));
				} else {
					pageable = PageRequest.of(paginationPayLoad.getPageIndex(), paginationPayLoad.getPageSize());
				}

				if (paginationPayLoad.getKeyword() != null && !paginationPayLoad.getKeyword().isBlank()) {
					page = userRepository.findAllUser(paginationPayLoad.getKeyword(), pageable);
				} else {
					page = userRepository.findAllUsers(pageable);

				}
			} else {
				if (!paginationPayLoad.getKeyword().isBlank()) {
					userList = userRepository.findAllUserWithSearching(paginationPayLoad.getKeyword());
				} else {
					userList = userRepository.findAllUsersWithoutSearching();
				}
			}

			if (page != null && !page.isEmpty()) {
				userList = page.getContent();
			}

			for (User user : userList) {
				UserResponse userResponse = new UserResponse();
				BeanUtils.copyProperties(user, userResponse);
				userResonseList.add(userResponse);
			}
			userResponsePage.setUserResponseList(userResonseList);
			userResponsePage.setPageIndex(page != null ? page.getNumber() : null);
			userResponsePage.setPageSize(page != null ? page.getSize() : null);
			userResponsePage.setTotalElement(page != null ? page.getTotalElements() : userList.size());
			userResponsePage.setTotalPages(page != null ? page.getTotalPages() : null);
			userResponsePage.setIsLastPage(page != null ? page.isLast() : null);
			userResponsePage.setIsFirstPage(page != null ? page.isFirst() : null);

			System.out.println("searching data....." + userResponsePage);
			log.info("User list found successfully  ");
			response.put(Constant.HTTP_STATUS, Constant.OK);
			response.put(Constant.MESSAGE, Constant.SUCCESS);
			response.put(Constant.DATA, userResponsePage);
		} catch (DataAccessResourceFailureException e) {
			response.put(Constant.RESPONSE_CODE, Constant.DB_CONNECTION_ERROR);
			response.put(Constant.MESSAGE, Constant.NO_DB_SERVER_CONNECTION);
			log.error("Exception : " + e.getMessage());
		} catch (Exception e) {
			log.error("Something went wrong to fetch out list");
			response.put(Constant.HTTP_STATUS, Constant.SERVER_ERROR);
			response.put(Constant.MESSAGE, Constant.PLEASE_TRY_AGAIN);
			response.put(Constant.DATA, userList);
		}
		return response;
	}

	@Override
	public Map<String, Object> addUser(UserRequestPayload userPayload) {
		Map<String, Object> response = new HashMap<>();
		String otpstr = "";
		try {
			if (userPayload != null) {
				int count = 0;
				UserLogs userLogs = new UserLogs();
				UserResponse userResponse = new UserResponse();
				User olduser = userRepository.findByPhoneNoAndCountryCode(userPayload.getPhoneNo(),
						userPayload.getCountryCode());
				if (olduser != null) {
					BeanUtils.copyProperties(olduser, userResponse);
					if (olduser.getDeviceId().equals(userPayload.getDeviceId())) {
						if (olduser.getStatus().equals(Constant.THREE)) {
							// Sending OTP if user not verified
							otpstr = utilityServiceImpl.sendOtp(olduser);
							response.put(Constant.HTTP_STATUS, Constant.OK);
							response.put(Constant.MESSAGE, Constant.OTP_SEND);
							response.put(Constant.DATA, userResponse);
							response.put("otp", otpstr);
							log.info(Constant.OTP_SEND + "! status - {}", olduser.getPhoneNo());
							/* save user logs */
							userLogs.setPhoneNo(olduser.getPhoneNo());
							userLogs.setEmail(olduser.getEmail());
							userLogs.setLastLoginAt(new Date());
							userLogsRepository.save(userLogs);
							log.info(" user logs details save succesesfully ! status - {}", Constant.OK);
						} else if (olduser.getStatus().equals(Constant.ONE)) {
							// Sending OTP every time before login
							otpstr = utilityServiceImpl.sendOtp(olduser);
							response.put(Constant.HTTP_STATUS, Constant.OK);
							response.put(Constant.MESSAGE, Constant.OTP_SEND);
							response.put(Constant.DATA, userResponse);
							response.put("otp", otpstr);
							log.info(Constant.OTP_SEND + "! status - {}", olduser.getPhoneNo());
							/* save user logs */
							userLogs.setPhoneNo(olduser.getPhoneNo());
							userLogs.setEmail(olduser.getEmail());
							userLogs.setLastLoginAt(new Date());
							userLogsRepository.save(userLogs);
							log.info(" user logs details save succesesfully ! status - {}", Constant.OK);
						} else if (olduser.getStatus().equals(Constant.TWO)) {
							response.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
							response.put(Constant.MESSAGE, Constant.MOBILE_BLOCKED_MESSAGE);
							log.info(Constant.MOBILE_BLOCKED_MESSAGE + "! status - {}", olduser.getPhoneNo());
						} else if (olduser.getStatus().equals(Constant.ZERO)) {
							response.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
							response.put(Constant.MESSAGE, Constant.MOBILE_DELETED_MESSAGE);
							log.info(Constant.MOBILE_DELETED_MESSAGE + "! status - {}", olduser.getPhoneNo());
						}
					} else {
						otpstr = utilityServiceImpl.sendOtp(olduser);
						BeanUtils.copyProperties(userPayload, olduser);
						olduser.setUpdationDate(new Date());
						olduser = userRepository.save(olduser);
						BeanUtils.copyProperties(olduser, userResponse);
						log.info("User registered with new device ! status - {}", olduser);
						response.put(Constant.HTTP_STATUS, Constant.OK);
						response.put(Constant.MESSAGE, Constant.OTP_SEND);
						response.put(Constant.DATA, userResponse);
						response.put("otp", otpstr);
						log.info(Constant.OTP_SEND + "! status - {}", olduser.getPhoneNo());
						/* save user logs */
						userLogs.setPhoneNo(olduser.getPhoneNo());
						userLogs.setEmail(olduser.getEmail());
						userLogs.setLastLoginAt(new Date());
						userLogsRepository.save(userLogs);
						log.info(" user logs details save succesesfully ! status - {}", Constant.OK);
					}
				} else {
					// New user registration
					User newuser = new User();
					BeanUtils.copyProperties(userPayload, newuser);
					newuser.setStatus(Constant.THREE);
					newuser = userRepository.save(newuser);
					log.info("Registered successfully, but not verified, please verify! status - {}", newuser);
					otpstr = utilityServiceImpl.sendOtp(newuser);
					BeanUtils.copyProperties(newuser, userResponse);
					response.put(Constant.HTTP_STATUS, Constant.OK);
					response.put(Constant.MESSAGE, Constant.OTP_SEND);
					response.put(Constant.DATA, userResponse);
					response.put("otp", otpstr);
					log.info(Constant.OTP_SEND + "! status - {}", userPayload.getPhoneNo());
					/* save user logs */
					userLogs.setPhoneNo(userPayload.getPhoneNo());
					userLogs.setEmail(null);
					userLogs.setLastLoginAt(new Date());
					userLogsRepository.save(userLogs);
					log.info(" user logs details save succesesfully ! status - {}", Constant.OK);
				}
			} else {
				response.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
				response.put(Constant.MESSAGE, Constant.BAD_REQUEST_MESSAGE);
				log.info(Constant.BAD_REQUEST_MESSAGE + "! status - {}", Constant.BAD_REQUEST);
			}
		} catch (DataAccessResourceFailureException e) {
			response.put(Constant.HTTP_STATUS, Constant.DB_CONNECTION_ERROR);
			response.put(Constant.MESSAGE, Constant.NO_SERVER_CONNECTION);
			log.error("Exception : " + e.getMessage());
		} catch (Exception e) {
			response.put(Constant.HTTP_STATUS, Constant.SERVER_ERROR);
			response.put(Constant.MESSAGE, Constant.SERVER_MESSAGE);
			log.error("Exception : " + e.getMessage());
		}
		return response;
	}

	@Override
	public Map<String, Object> block_update(Long id, Short status) {
		Map<String, Object> response = new HashMap<>();
		User user = null;
		try {
			user = userRepository.findById(id).orElse(null);
			if (user != null) {
				/* ---------------Here user delete can't block & unblock ------------ */
				if (!(user.getStatus().equals(Constant.ZERO))) {
					/* ---------------Here unblock the user------------ */
					log.info("user found status - {}", user);
					if (status.equals(Constant.ONE)) {
						if (user.getStatus().equals(Constant.ONE)) {
							response.put(Constant.HTTP_STATUS, Constant.CONFLICT);
							response.put(Constant.MESSAGE, Constant.ALREADY_UNBLOCKED_MESSAGE);
							log.info("Already unblocked! status - {}", Constant.CONFLICT);
							return response;
						} else {
							user.setStatus(status);
							user.setUpdationDate(new Date());
							user = userRepository.save(user);
							response.put(Constant.HTTP_STATUS, Constant.OK);
							response.put(Constant.MESSAGE, Constant.UNBLOCKED_SUCCESS_MESSAGE);
							log.info("unblocked successfully! status - {}", Constant.OK);
						}
						/* ---------------Here block the user------------ */
					} else if (status.equals(Constant.TWO)) {
						if (user.getStatus().equals(Constant.TWO)) {
							response.put(Constant.HTTP_STATUS, Constant.CONFLICT);
							response.put(Constant.MESSAGE, Constant.ALREADY_BLOCKED_MESSAGE);
							log.info("Already unblocked! status - {}", Constant.CONFLICT);
							return response;
						} else {
							user.setStatus(status);
							user.setUpdationDate(new Date());
							user = userRepository.save(user);
							response.put(Constant.HTTP_STATUS, Constant.OK);
							response.put(Constant.MESSAGE, Constant.BLOCKED_SUCCESS_MESSAGE);
							log.info("unblocked successfully! status - {}", Constant.OK);
						}
					} else {
						response.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
						response.put(Constant.MESSAGE, Constant.STATUS_INVALID_MESSAGE);
						log.info("Status value invalid! status - {}", Constant.BAD_REQUEST);
					}
				} else {
					response.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
					response.put(Constant.MESSAGE, Constant.USER_DELETED_CANT_BLOCK_UNBLOCK);
					log.info("User Already Deleted ! status - {}", Constant.CONFLICT);
				}
			} else {
				response.put(Constant.HTTP_STATUS, Constant.OK);
				response.put(Constant.MESSAGE, Constant.RECORD_NOT_FOUND_MESSAGE);
				log.info("Record not found! status - {}", Constant.OK);
			}
		} catch (DataAccessResourceFailureException e) {
			response.put(Constant.RESPONSE_CODE, Constant.DB_CONNECTION_ERROR);
			response.put(Constant.MESSAGE, Constant.NO_DB_SERVER_CONNECTION);
			log.error("Exception : " + e.getMessage());
		} catch (Exception e) {
			log.error("Error to find user by mobile ", user);
			response.put(Constant.HTTP_STATUS, HttpStatus.BAD_REQUEST);
			response.put(Constant.MESSAGE, Constant.PLEASE_TRY_AGAIN);
			response.put(Constant.DATA, null);
		}
		return response;
	}

	@Override
	public Map<String, Object> savePolicy(String messsage, Integer type) {
		Map<String, Object> map = new HashMap<>();
		TncAndPolicy tncAndPolicy = new TncAndPolicy();
		try {
			if (messsage != null && !messsage.isEmpty() && type != 0) {
				if (type == 1) {
					tncAndPolicy = tncAndConditionRepository.findByName("Terms & Condition");
					/*--------------- here update terms & condition------------------- */
					if (tncAndPolicy != null) {
						if (tncAndPolicy.getDescription().equals(messsage)) {
							map.put(Constant.HTTP_STATUS, Constant.OK);
							map.put(Constant.MESSAGE, Constant.AlREADY_UPDATED_TNC);
							log.info("Terms & condition already updated" + messsage);
						} else {
							tncAndPolicy.setDescription(messsage);
							tncAndConditionRepository.save(tncAndPolicy);
							map.put(Constant.HTTP_STATUS, Constant.OK);
							map.put(Constant.MESSAGE, Constant.TNC_UPDATED);
							log.info("Updated Terms & condition successfully" + messsage);
						}
					} else {
						/*---------------here add terms & condition---------------*/
						TncAndPolicy newtncAndPolicy = new TncAndPolicy();
						newtncAndPolicy.setName("Terms & Condition");
						newtncAndPolicy.setDescription(messsage);
						tncAndConditionRepository.save(newtncAndPolicy);
						map.put(Constant.HTTP_STATUS, Constant.OK);
						map.put(Constant.MESSAGE, Constant.TNC);
						log.info("Terms & condition successfully" + messsage);
					}
				} else if (type == 2) {
					tncAndPolicy = tncAndConditionRepository.findByName("Privacy & Policy");
					/*--------------- here Privacy & Policy------------------- */
					if (tncAndPolicy != null) {
						if (tncAndPolicy.getDescription().equals(messsage)) {
							map.put(Constant.HTTP_STATUS, Constant.OK);
							map.put(Constant.MESSAGE, Constant.AlREADY_UPDATED_POLICY);
							log.info("Privacy & policy already updated" + messsage);
						} else {
							tncAndPolicy.setDescription(messsage);
							tncAndConditionRepository.save(tncAndPolicy);
							map.put(Constant.HTTP_STATUS, Constant.OK);
							map.put(Constant.MESSAGE, Constant.POLICY_UPDATED);
							log.info("Updated Terms & condition successfully" + messsage);
						}
					} else {
						/*---------------here add Privacy & Policy---------------*/
						TncAndPolicy newtncAndPolicy = new TncAndPolicy();
						newtncAndPolicy.setName("Privacy & Policy");
						newtncAndPolicy.setDescription(messsage);
						tncAndConditionRepository.save(newtncAndPolicy);
						map.put(Constant.HTTP_STATUS, Constant.OK);
						map.put(Constant.MESSAGE, Constant.POLICY);
						log.info("Privacy & policy uploaded successfully " + messsage);
					}
				} else {
					map.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
					map.put(Constant.MESSAGE, Constant.MSG_TYPE_NOT_NULL);
					log.info("Type can not null, it should be valid! 1 or 2 status - {}", Constant.BAD_REQUEST);
				}
			} else {
				map.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
				map.put(Constant.MESSAGE, Constant.MSG_TYPE_NOT_NULL);
				log.info("Type can not null, it should be valid! 1 or 2 status - {}", Constant.BAD_REQUEST);
			}
		} catch (DataAccessResourceFailureException e) {
			map.put(Constant.RESPONSE_CODE, Constant.DB_CONNECTION_ERROR);
			map.put(Constant.MESSAGE, Constant.NO_DB_SERVER_CONNECTION);
			log.error("Exception : " + e.getMessage());
		} catch (Exception e) {
			map.put(Constant.HTTP_STATUS, Constant.NOT_AUTHORIZED);
			map.put(Constant.MESSAGE, Constant.ERROR_MESSAGE);
			log.error("Error to find terms & condition or privacy & policy ");
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteUser(Long id) {
		Map<String, Object> map = new HashMap<>();
		try {
			if (id != null) {
				User user = userRepository.findById(id).orElse(null);
				if (user != null) {
					if (user.getStatus().equals(Constant.ZERO)) {
						map.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
						map.put(Constant.MESSAGE, Constant.ALREADY_DELETED_MESSAGE);
						log.info("Already deleted! status - {}", Constant.CONFLICT);
					} else {
						user.setStatus(Constant.ZERO);
						user.setUpdationDate(new Date());
						user = userRepository.save(user);
						map.put(Constant.HTTP_STATUS, Constant.OK);
						map.put(Constant.MESSAGE, Constant.DELETED_MESSAGE);
						log.info("Deleted successfully! status - {}", Constant.OK);
					}
				} else {
					map.put(Constant.HTTP_STATUS, Constant.OK);
					map.put(Constant.MESSAGE, Constant.ID_NOT_FOUND_MESSAGE);
					log.info("Given id not found into the database! status - {}", Constant.OK);
				}
			} else {
				map.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
				map.put(Constant.MESSAGE, Constant.ID_CAN_NOT_NULL_MESSAGE);
				log.info("Id can not null, it should be valid! status - {}", Constant.BAD_REQUEST);
			}
		} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
			map.put(Constant.HTTP_STATUS, Constant.DB_CONNECTION_ERROR);
			map.put(Constant.MESSAGE, Constant.NO_SERVER_CONNECTION);
			log.error("Exception : " + e.getMessage());
		} catch (Exception e) {
			map.put(Constant.HTTP_STATUS, Constant.SERVER_ERROR);
			map.put(Constant.MESSAGE, Constant.SERVER_MESSAGE);
			log.error("Exception : " + e.getMessage());
		}
		return map;
	}

	@Override
	public Map<String, Object> userVerify(VerifyRequestPayload payload) {
		Map<String, Object> map = new HashMap<>();
		try {
			UserResponse userResponse = new UserResponse();
			if (payload != null) {
				User user = userRepository.findUser(payload.getPhoneNo(), payload.getCountryCode());
				if (user != null) {
					log.info(Constant.FOUND_MESSAGE + "! status - {}", user);
					if (user.getStatus().equals(Constant.ONE) || user.getStatus().equals(Constant.THREE)) {
						Otp otp = userOtpRepository.findByUserIdFk(user.getId());
						if (otp != null) {
							Date date = otp.getCreationDate();
							long otpTimestampMillis = date.getTime(); // Replace with your OTP timestamp
							long currentTimestampMillis = Instant.now().toEpochMilli();
							/* Check if OTP is within a 15-second window */
							long timeDifferenceSeconds = ChronoUnit.SECONDS.between(
									Instant.ofEpochMilli(otpTimestampMillis),
									Instant.ofEpochMilli(currentTimestampMillis));
							if (timeDifferenceSeconds <= 15) {
								System.out.println("OTP is valid.");
								log.info("user otp found " + otp);
								if (otp.getOtp().equals(payload.getOtp())) {
									/* save user */
									user.setStatus(Constant.ONE);
									user.setUpdationDate(new Date());
									user = userRepository.save(user);
									userOtpRepository.deleteByUserIdFk(user.getId());
									BeanUtils.copyProperties(user, userResponse);
									map.put(Constant.HTTP_STATUS, Constant.OK);
									map.put(Constant.MESSAGE, Constant.USER_VERIFY_SUCCESSFULY);
									map.put(Constant.DATA, userResponse);
									log.info(Constant.USER_VERIFY_SUCCESSFULY + "! status - {}", user);
								} else {
									map.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
									map.put(Constant.MESSAGE, Constant.INCORRECT_OTP);
									log.info(Constant.INCORRECT_OTP + "! status - {}", Constant.BAD_REQUEST);
								}
							} else {
								userOtpRepository.deleteByUserIdFk(user.getId());
								map.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
								map.put(Constant.MESSAGE, "OTP has expired !!");
								log.info("OTP has expired ! status - {}", Constant.BAD_REQUEST);
							}
						} else {
							map.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
							map.put(Constant.MESSAGE, Constant.INVALID_MOBILE_AND_OTP_MESSAGE);
							log.info(Constant.INVALID_MOBILE_AND_OTP_MESSAGE + " status - {}", Constant.BAD_REQUEST);
						}
					} else if (user.getStatus().equals(Constant.ZERO)) {
						map.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
						map.put(Constant.MESSAGE, Constant.MOBILE_DELETED_MESSAGE);
						log.info(Constant.MOBILE_DELETED_MESSAGE + "! status - {}", Constant.BAD_REQUEST);
					} else if (user.getStatus().equals(Constant.TWO)) {
						map.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
						map.put(Constant.MESSAGE, Constant.MOBILE_BLOCKED_MESSAGE);
						log.info(Constant.MOBILE_BLOCKED_MESSAGE + "! status - {}", Constant.BAD_REQUEST);
					}
				} else {
					map.put(Constant.HTTP_STATUS, Constant.OK);
					map.put(Constant.MESSAGE, Constant.NOT_FOUND_MESSAGE);
					log.info(Constant.NOT_FOUND_MESSAGE + "! status - {}", Constant.OK);
				}
			} else {
				map.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
				map.put(Constant.MESSAGE, Constant.BAD_REQUEST_MESSAGE);
				log.info(Constant.BAD_REQUEST_MESSAGE + "! status - {}", Constant.BAD_REQUEST);
			}
		} catch (DataAccessResourceFailureException e) {
			map.put(Constant.RESPONSE_CODE, Constant.DB_CONNECTION_ERROR);
			map.put(Constant.MESSAGE, Constant.NO_DB_SERVER_CONNECTION);
			log.error("Exception : " + e.getMessage());
		} catch (Exception e) {
			map.put(Constant.HTTP_STATUS, Constant.SERVER_ERROR);
			map.put(Constant.MESSAGE, e.getMessage());
			log.error("Exception : " + e.getMessage());
		}
		return map;
	}

	@Override
	public Map<String, Object> getTncAndPolicy(Integer type) {
		Map<String, Object> response = new HashMap<>();
		try {
			if (type != null) {
				if (type == 1) {
					TncAndPolicy termAndCondition = tncAndConditionRepository.findByName("Terms & Condition");
					if (termAndCondition != null) {
						log.info("data found !!" + termAndCondition);
						response.put(Constant.HTTP_STATUS, Constant.OK);
						response.put(Constant.MESSAGE, Constant.SUCCESS);
						response.put(Constant.DATA, termAndCondition);
					} else {
						response.put(Constant.HTTP_STATUS, Constant.OK);
						response.put(Constant.MESSAGE, Constant.TNC_AND_POLICY);
						log.info("data not found");
					}
				} else if (type == 2) {
					TncAndPolicy privacyAndPolicy = tncAndConditionRepository.findByName("Privacy & Policy");
					if (privacyAndPolicy != null) {
						log.info("data found !!" + privacyAndPolicy);
						response.put(Constant.HTTP_STATUS, Constant.OK);
						response.put(Constant.MESSAGE, Constant.SUCCESS);
						response.put(Constant.DATA, privacyAndPolicy);
					} else {
						response.put(Constant.HTTP_STATUS, Constant.OK);
						response.put(Constant.MESSAGE, Constant.TNC_AND_POLICY);
						log.info("data not found");
					}
				} else {
					response.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
					response.put(Constant.MESSAGE, Constant.MSG_TYPE_NOT_NULL);
					log.info("Type can not null, it should be valid! 1 or 2 status - {}", Constant.BAD_REQUEST);
				}
			} else {
				response.put(Constant.HTTP_STATUS, Constant.BAD_REQUEST);
				response.put(Constant.MESSAGE, Constant.MSG_TYPE_NOT_NULL);
				log.info("Type can not null, it should be valid! 1 or 2 status - {}", Constant.BAD_REQUEST);
			}
		} catch (DataAccessResourceFailureException e) {
			response.put(Constant.RESPONSE_CODE, Constant.DB_CONNECTION_ERROR);
			response.put(Constant.MESSAGE, Constant.NO_DB_SERVER_CONNECTION);
			log.error("Exception : " + e.getMessage());
		} catch (Exception e) {
			response.put(Constant.HTTP_STATUS, Constant.SERVER_ERROR);
			response.put(Constant.MESSAGE, Constant.SERVER_MESSAGE);
			log.error("Exception : " + e.getMessage());
		}
		return response;
	}

	@Override
	public Map<String, Object> updateProfileImage(MultipartFile profile_image, Long userId) {
		Map<String, Object> map = new HashMap<>();
		try {
			// Check if the profile_image is not null
			if (profile_image != null && !profile_image.isEmpty()) {
				Optional<User> optionalUser = userRepository.findById(userId);
				if (optionalUser.isPresent()) {
					User oldUser = optionalUser.get();
					UserResponse userResponse = new UserResponse();
					// Upload the new profile image and set the image URL
					String imagePath = imageUrl
							+ FileUploader.uploadFile(profile_image, imageUploadDir, oldUser.getId());
					log.info("Image update successful! Image path: {}", imagePath);
					oldUser.setImageUrl(imagePath);
					// Save the updated user object
					userRepository.save(oldUser);
					BeanUtils.copyProperties(oldUser, userResponse);
					map.put(Constant.RESPONSE_CODE, Constant.OK);
					map.put(Constant.MESSAGE, Constant.PROFILE_UPDATED_MESSAGE);
					map.put(Constant.DATA, userResponse); // Including userResponse instead of portfolioResponsesList
															// here
				} else {
					map.put(Constant.RESPONSE_CODE, Constant.OK);
					map.put(Constant.MESSAGE, "User not found");
				}
			} else {
				map.put(Constant.RESPONSE_CODE, Constant.BAD_REQUEST);
				map.put(Constant.MESSAGE, "Invalid profile image");
			}
		} catch (DataAccessResourceFailureException e) {
			map.put(Constant.RESPONSE_CODE, Constant.DB_CONNECTION_ERROR);
			map.put(Constant.MESSAGE, Constant.NO_DB_SERVER_CONNECTION);
			log.error("Exception : " + e.getMessage());
		} catch (Exception e) {
			map.put(Constant.RESPONSE_CODE, Constant.SERVER_ERROR);
			map.put(Constant.MESSAGE, "Error updating profile image");
			log.error("Error updating profile image for userId: {}", userId, e);
		}
		return map;
	}

	@Override
	public Map<String, Object> updateProfile(UserProfileRequestPayload profileRequestPayload) {
		Map<String, Object> map = new HashMap<>();
		UserResponse userResponse = new UserResponse();
		try {
			User oldUser = userRepository.findByIdAndStatus(profileRequestPayload.getUserId(), Constant.ONE);
			if (oldUser != null) {
				log.info("Record found! status - {}", oldUser);
				oldUser.setName(profileRequestPayload.getName());
				oldUser.setEmail(profileRequestPayload.getEmail());
				oldUser.setUpdationDate(new Date());
				oldUser = userRepository.save(oldUser);
				BeanUtils.copyProperties(oldUser, userResponse);
				map.put(Constant.RESPONSE_CODE, Constant.OK);
				map.put(Constant.MESSAGE, Constant.PROFILE_UPDATED_MESSAGE);
				map.put(Constant.DATA, userResponse);
				log.info("Profile updated successfully! status - {}", userResponse);
			} else {
				map.put(Constant.RESPONSE_CODE, Constant.NOT_FOUND);
				map.put(Constant.MESSAGE, Constant.PROFILE_NOT_UPDATED_MESSAGE);
				log.info("Profile not updated because the given user id was not found in the database! status - {}",
						Constant.NOT_FOUND);
			}
		} catch (DataAccessResourceFailureException e) {
			map.put(Constant.RESPONSE_CODE, Constant.DB_CONNECTION_ERROR);
			map.put(Constant.MESSAGE, Constant.NO_DB_SERVER_CONNECTION);
			log.error("Exception : " + e.getMessage());
		} catch (Exception e) {
			map.put(Constant.RESPONSE_CODE, Constant.SERVER_ERROR);
			map.put(Constant.MESSAGE, Constant.SERVER_MESSAGE);
			log.error("Exception : " + e.getMessage());
		}
		return map;
	}

	@Override
	public Map<String, Object> getProfileById(Long userId) {
		Map<String, Object> map = new HashMap<>();
		try {
			User user = userRepository.findByIdAndStatus(userId, Constant.ONE);
			if (user != null) {
				map.put(Constant.HTTP_STATUS, Constant.OK);
				map.put(Constant.MESSAGE, Constant.DATA_FOUND_MESSAGE);
				map.put(Constant.DATA, user);
			} else {
				map.put(Constant.HTTP_STATUS, Constant.OK);
				map.put(Constant.MESSAGE, Constant.INVALID_PHONE_NUMBER);
			}
		} catch (DataAccessResourceFailureException e) {
			map.put(Constant.RESPONSE_CODE, Constant.DB_CONNECTION_ERROR);
			map.put(Constant.MESSAGE, Constant.NO_DB_SERVER_CONNECTION);
			log.error("Exception : " + e.getMessage());
		} catch (Exception e) {
			map.put(Constant.RESPONSE_CODE, Constant.SERVER_ERROR);
			map.put(Constant.MESSAGE, Constant.PLEASE_TRY_AGAIN);
			log.error("Exception:-" + e.getMessage());
		}
		return map;
	}

	@Override
	public Map<String, Object> getUserCount() {
		Map<String, Object> map = new HashMap<>();
		CountResponse countResponse = new CountResponse();
		Long userCount = 0L, activeCount = 0L, inactiveCount = 0L, deletedCount = 0L, verifiedCount = 0L;
		try {
			/*---------------- get all register users count ------------*/
			userCount = userRepository.findAllUsersCount();
			log.info("total count :- " + userCount);
			if (userCount != null) {
				countResponse.setTotal(userCount);
				/*--------------- get all active user count ---------------*/
				activeCount = userRepository.findAllActiveUsersCount();
				log.info("Active user count :- " + activeCount);
				if (activeCount != null) {
					countResponse.setActive(activeCount);
				} else {
					countResponse.setActive(0L);
					log.info("Active users not found :- " + activeCount);
				}
				/*--------------- get all inactive user count ---------------*/
				inactiveCount = userRepository.findAllInactiveUsersCount();
				log.info("Inactive user count :- " + inactiveCount);
				if (inactiveCount != null) {
					countResponse.setInactive(inactiveCount);
				} else {
					countResponse.setInactive(0L);
					log.info("Inactive users not found :- " + inactiveCount);
				}
				/*--------------- get all deleted user count -----------------*/
				deletedCount = userRepository.findAllDeletedUsersCount();
				log.info(" Deleted user count :- " + deletedCount);
				if (deletedCount != null) {
					countResponse.setDeleted(deletedCount);
				} else {
					countResponse.setDeleted(0L);
					log.info("Deleted users not found :- " + deletedCount);
				}
				/*--------------- get all not verified user count -----------------*/
				verifiedCount = userRepository.findAllNotVerifiedUSersCount();
				log.info(" Not verified user count :- " + verifiedCount);
				if (verifiedCount != null) {
					countResponse.setNot_verified(verifiedCount);
				} else {
					countResponse.setNot_verified(0L);
					log.info(" Not verified user count not found :- " + verifiedCount);
				}
				map.put(Constant.RESPONSE_CODE, Constant.OK);
				map.put(Constant.MESSAGE, Constant.USER_COUNT);
				map.put(Constant.DATA, countResponse);
				log.info("data found successfully !! " + countResponse);
			} else {
				countResponse.setActive(activeCount);
				countResponse.setInactive(inactiveCount);
				countResponse.setTotal(userCount);
				countResponse.setDeleted(deletedCount);
				countResponse.setNot_verified(verifiedCount);
				map.put(Constant.RESPONSE_CODE, Constant.OK);
				map.put(Constant.MESSAGE, Constant.USER_NOT_FOUND);
				map.put(Constant.DATA, countResponse);
				log.info("data not found" + countResponse);
			}
		} catch (DataAccessResourceFailureException e) {
			map.put(Constant.RESPONSE_CODE, Constant.DB_CONNECTION_ERROR);
			map.put(Constant.MESSAGE, Constant.NO_DB_SERVER_CONNECTION);
			log.error("Exception : " + e.getMessage());
		} catch (Exception e) {
			map.put(Constant.RESPONSE_CODE, Constant.SERVER_ERROR);
			map.put(Constant.MESSAGE, Constant.PLEASE_TRY_AGAIN);
			log.error("Exception:-" + e.getMessage());
		}
		return map;
	}

	@Override
	public Map<String, Object> getUserGraph(String type) {
		Map<String, Object> map = new HashMap<>();
		UserGraphResponse graphResponse = new UserGraphResponse();
		try {
			List<MonthObject> list = new ArrayList<MonthObject>();
			Long totalCount = 0L;
			Year currentYear = Year.now();

//			String[] starting_month = { "", "", "", "", "", "", "", "", "", "", "", "" };
//			String[] ending_month = { "", "", "", "", "", "", "", "", "", "", "", "" };
//			String[] month = { "", "", "", "", "", "", "", "", "", "", "", "" };

//			LocalDate currentDate = LocalDate.now();
//			LocalDate startDate = null;
//			startDate = currentDate;
//			LocalDate endDate = null;
//
//			// Loop through 12 months
//			for (int i = 1; i <= 12; i++) {
//				int length = 0;
//				Month mon = startDate.getMonth();
//				if (mon.getValue() == 1) {
//					length = Month.JANUARY.maxLength();
//				}
//				if (mon.getValue() == 2) {
//					if (currentYear.isLeap())
//						length = Month.FEBRUARY.maxLength();
//					else
//						length = Month.FEBRUARY.maxLength() - 1;
//				}
//				if (mon.getValue() == 3) {
//					length = Month.MARCH.maxLength();
//				}
//				if (mon.getValue() == 4) {
//					length = Month.APRIL.maxLength();
//				}
//				if (mon.getValue() == 5) {
//					length = Month.MAY.maxLength();
//				}
//				if (mon.getValue() == 6) {
//					length = Month.JUNE.maxLength();
//				}
//				if (mon.getValue() == 7) {
//					length = Month.JULY.maxLength();
//				}
//				if (mon.getValue() == 8) {
//					length = Month.AUGUST.maxLength();
//				}
//				if (mon.getValue() == 9) {
//					length = Month.SEPTEMBER.maxLength();
//				}
//				if (mon.getValue() == 10) {
//					length = Month.OCTOBER.maxLength();
//				}
//				if (mon.getValue() == 11) {
//					length = Month.NOVEMBER.maxLength();
//				}
//				if (mon.getValue() == 12) {
//					length = Month.DECEMBER.maxLength();
//				}
//				int dt = startDate.getDayOfMonth();
//				startDate = startDate.minusDays(dt - 1);
//				endDate = startDate.plusDays(length - 1);
//				starting_month[mon.getValue() - 1] = startDate.toString();
//				ending_month[mon.getValue() - 1] = endDate.toString();
//				month[mon.getValue() - 1] = startDate.getMonth().toString();
////				startDate = startDate.minusMonths(1);
//
//				// new add
//				if (type.equalsIgnoreCase("Day")) {
//					break;
//				} else if (type.equalsIgnoreCase("Month")) {
//					startDate = startDate.minusMonths(1);
//				} else if (type.equalsIgnoreCase("Year")) {
//					break;
//				}
//			}

			/* new chnges 03/03 */
			List<String> starting_months = new ArrayList<String>();
			List<String> end_months = new ArrayList<String>();
			List<String> months = new ArrayList<String>();

			int currentYear1 = LocalDate.now().getYear();
			for (Month month1 : Month.values()) {
				LocalDate firstDayOfMonth = LocalDate.of(currentYear1, month1, 1);
				starting_months.add(firstDayOfMonth.toString());
				months.add(firstDayOfMonth.getMonth().toString());
				System.out.println("size of starting month :- :- " + starting_months.size());
			}

			for (int mon = 1; mon <= 12; mon++) {
				YearMonth yearMonth = YearMonth.of(currentYear1, mon);
				end_months.add(yearMonth.atEndOfMonth().toString());
				System.out.println("size of ending month :- :- " + end_months.size());
			}

			LocalDate currentDate = LocalDate.now();
			LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);
			LocalDate lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			System.out.println("First day of the month: " + firstDayOfMonth.format(formatter));
			System.out.println("Last day of the month: " + lastDayOfMonth.format(formatter));

			if (type.equalsIgnoreCase("Day")) {
				while (firstDayOfMonth.equals(lastDayOfMonth) || firstDayOfMonth.isBefore(lastDayOfMonth)) {
					totalCount = userRepository.findAllUserCurrentDate(firstDayOfMonth.toString());
					if (totalCount != 0) {
						Long active = userRepository.findAllCurrentDayActiveUsersCount(firstDayOfMonth.toString());
						MonthObject monthObject = new MonthObject();
						monthObject.setDateFormat(firstDayOfMonth.format(formatter).toString());
						monthObject.setCount(totalCount);
						monthObject.setActive(active);
						list.add(monthObject);
					} else {
						MonthObject monthObject = new MonthObject();
						monthObject.setDateFormat(firstDayOfMonth.format(formatter).toString());
						monthObject.setCount(totalCount);
						monthObject.setActive(0L);
						list.add(monthObject);
					}
					firstDayOfMonth = firstDayOfMonth.plusDays(1);
				}
			} else if (type.equalsIgnoreCase("Month")) {
				for (int i = 0; i <= starting_months.size() - 1; i++) {
					totalCount = userRepository.findAllUserCurrentMonth(starting_months.get(i), end_months.get(i));
					if (totalCount != 0) {
						Long active = userRepository.findAllCurrentMonthActiveUsersCount(starting_months.get(i),
								end_months.get(i));
						MonthObject monthObject = new MonthObject();
						monthObject.setDateFormat(months.get(i));
						monthObject.setCount(totalCount);
						monthObject.setActive(active);
						list.add(monthObject);
					} else {
						MonthObject monthObject = new MonthObject();
						monthObject.setDateFormat(months.get(i));
						monthObject.setCount(totalCount);
						monthObject.setActive(0L);
						list.add(monthObject);
					}
				}
			} else if (type.equalsIgnoreCase("Year")) {
				int previousYear = currentYear.getValue();
				while (previousYear >= 2023) {
					log.info(currentYear.getValue() + " total user count :-"
							+ userRepository.findAllUserCurrentYear(String.valueOf(previousYear)));
					totalCount = userRepository.findAllUserCurrentYear(String.valueOf(previousYear));
					if (totalCount != null) {
						Long active = userRepository
								.findAllUserCurrentYearActiveUsersCount(String.valueOf(previousYear));
						MonthObject monthObject = new MonthObject();
						monthObject.setDateFormat(String.valueOf(previousYear));
						monthObject.setCount(totalCount);
						monthObject.setActive(active);
						list.add(monthObject);
					} else {
						MonthObject monthObject = new MonthObject();
						monthObject.setDateFormat(String.valueOf(previousYear));
						monthObject.setCount(totalCount);
						monthObject.setActive(0L);
						list.add(monthObject);
					}
					previousYear = previousYear - 1;
				}
				Collections.reverse(list);
			} else {
				map.put(Constant.RESPONSE_CODE, Constant.BAD_REQUEST);
				map.put(Constant.MESSAGE, Constant.BAD_REQUEST_MESSAGE);
				map.put(Constant.DATA, type);
				log.info("Invalid type :- " + type);
				return map;
			}
			graphResponse.setLabel("Registered Users");
			graphResponse.setUserGraphResponseList(list);
			map.put(Constant.RESPONSE_CODE, Constant.OK);
			map.put(Constant.MESSAGE, Constant.RECORD_FOUND);
			map.put(Constant.DATA, graphResponse);
			log.info("data found !! status - {} " + Constant.OK + " " + graphResponse);
		} catch (DataAccessResourceFailureException e) {
			map.put(Constant.RESPONSE_CODE, Constant.DB_CONNECTION_ERROR);
			map.put(Constant.MESSAGE, Constant.NO_DB_SERVER_CONNECTION);
			log.error("Exception : " + e.getMessage());
		} catch (Exception e) {
			map.put(Constant.RESPONSE_CODE, Constant.SERVER_ERROR);
			map.put(Constant.MESSAGE, Constant.PLEASE_TRY_AGAIN);
			log.error("Exception:-" + e.getMessage());
		}
		return map;
	}

	@Override
	public List<User> getAllUserDetails(UserRequestPage userRequestPage) {
		List<User> userlist = new ArrayList<>();
		try {
			if (userRequestPage.getKeyword() != "" && userRequestPage.getKeyword() != null) {
				userlist = userRepository.findAllUserWithSearching(userRequestPage.getKeyword());
			} else {
				userlist = userRepository.findAllUsersWithoutSearching();
			}
		} catch (DataAccessResourceFailureException e) {
			log.error("Exception : " + e.getMessage());
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage());
		}
		return userlist;
	}

	public Map<String, Object> getAllUserLogs(UserLogsRequest paginationPayLoad) {
		Map<String, Object> map = new HashMap<>();
		Pageable pageable = null;
		Page<UserLogs> page = null;
		List<UserLogs> userLogList = new ArrayList<>();
		List<UserLogResponse> userLogResponseList = new ArrayList<>();
		UserLogResponsePage userLogResponsePage = new UserLogResponsePage();
		try {
			if (paginationPayLoad.getPageSize() != 0) {
				if (!paginationPayLoad.getStartDate().isBlank() && !paginationPayLoad.getEndDate().isBlank()) {
					pageable = PageRequest.of(paginationPayLoad.getPageIndex(), paginationPayLoad.getPageSize());
					if (paginationPayLoad.getKeyword() != null && !paginationPayLoad.getKeyword().isBlank()) {
						page = userLogsRepository.findAllUserLongWithSearchingAndFilter(paginationPayLoad.getKeyword(),
								paginationPayLoad.getStartDate() + " 00:00:00",
								paginationPayLoad.getEndDate() + " 23:59:00", pageable);
					} else {
						page = userLogsRepository.findAllUserLongWithOutSearchingWithFilter(
								paginationPayLoad.getStartDate() + " 00:00:00",
								paginationPayLoad.getEndDate() + " 23:59:00", pageable);
					}
				} else {
					pageable = PageRequest.of(paginationPayLoad.getPageIndex(), paginationPayLoad.getPageSize());
					if (paginationPayLoad.getKeyword() != null && !paginationPayLoad.getKeyword().isBlank()) {
						page = userLogsRepository.findAllUserLongWithSearching(paginationPayLoad.getKeyword(),
								pageable);
					} else {
						page = userLogsRepository.findAllUserLongWithOutSearching(pageable);
					}
				}

				if (page != null && !page.isEmpty()) {
					int count = 0;
					userLogList = page.getContent();
					for (UserLogs userLogs : userLogList) {
						count++;
						UserLogResponse userLogResponse = new UserLogResponse();
						BeanUtils.copyProperties(userLogs, userLogResponse);
						userLogResponse.setLastLoginAt(userLogs.getLastLoginAt().toString());
						userLogResponseList.add(userLogResponse);
					}
					userLogResponsePage.setUserLogResponseList(userLogResponseList);
					userLogResponsePage.setPageIndex(page.getNumber());
					userLogResponsePage.setPageSize(page.getSize());
					userLogResponsePage.setTotalElement(page.getTotalElements());
					userLogResponsePage.setTotalPages(page.getTotalPages());
					userLogResponsePage.setIsLastPage(page.isLast());
					userLogResponsePage.setIsFirstPage(page.isFirst());
					userLogResponsePage.setOtpCount(count);

					map.put(Constant.RESPONSE_CODE, Constant.OK);
					map.put(Constant.MESSAGE, Constant.RECORD_FOUND);
					map.put(Constant.DATA, userLogResponsePage);
					log.info(Constant.RECORD_FOUND + Constant.OK);
				} else {
					map.put(Constant.RESPONSE_CODE, Constant.NOT_FOUND);
					map.put(Constant.MESSAGE, Constant.RECORD_NOT_FOUND_MESSAGE);
					log.info(Constant.RECORD_NOT_FOUND_MESSAGE + Constant.NOT_FOUND);
					return map;
				}
			} else {
				map.put(Constant.RESPONSE_CODE, Constant.BAD_REQUEST);
				map.put(Constant.MESSAGE, Constant.PAGE_SIZE_MESSAGE);
				log.info(Constant.PAGE_SIZE_MESSAGE + " " + Constant.BAD_REQUEST);
			}
		} catch (Exception e) {
			map.put(Constant.RESPONSE_CODE, Constant.SERVER_ERROR);
			map.put(Constant.MESSAGE, Constant.PLEASE_TRY_AGAIN);
			log.error("Exception:-" + e.getMessage());
		}
		return map;
	}

}