package com.advantal.servicesImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.advantal.model.PlanDescription;
import com.advantal.model.PlanType;
import com.advantal.model.Product;
import com.advantal.model.User;
//import com.advantal.generators.SubscriptionCodeGenerator;
import com.advantal.model.UserPlan;
import com.advantal.model.UserSubscription;
import com.advantal.repository.PlanDescriptionRepository;
import com.advantal.repository.PlanTypeRepository;
import com.advantal.repository.ProductRepository;
import com.advantal.repository.UserPlanRepository;
import com.advantal.repository.UserRepository;
import com.advantal.repository.UserSubscriptionRepository;
import com.advantal.requestPayload.PlanDescriptionRequestPayload;
import com.advantal.requestPayload.SubscribeRequstPayload;
import com.advantal.requestPayload.SubscriptionRequestPayload;
import com.advantal.responsePayload.PlanDescriptionResponse;
import com.advantal.responsePayload.UserPlanResponse;
import com.advantal.responsePayload.UserSubscribeResponse;
import com.advantal.services.SubscriptionService;
import com.advantal.utils.Constant;
import com.advantal.utils.FileUploader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private UserPlanRepository userPlanRepository;

	@Autowired
	private PlanDescriptionRepository planDescriptionRepository;

	@Autowired
	private UserSubscriptionRepository userSubscriptionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PlanTypeRepository planTypeRepository;

	@Value("${spring.logodir}")
	private String logodir;

	@Value("${spring.logourl}")
	private String logourl;

	@Override
	public Map<String, Object> addSubscriptionPlan(SubscriptionRequestPayload subscriptionRequestPayload) {
		Map<String, Object> response = new HashMap<String, Object>();
		UserPlanResponse userPlanResponse = new UserPlanResponse();
		List<PlanDescriptionResponse> list = new ArrayList<PlanDescriptionResponse>();
		try {
			if (subscriptionRequestPayload.getId() > 0) {
				UserPlan userPlan = userPlanRepository.findByIdAndProductIdAndStatus(subscriptionRequestPayload.getId(),
						subscriptionRequestPayload.getProductId(), Constant.ONE);
				try {
					/* ........update subscription here......... */
					if (userPlan != null) {
						userPlan.setName(subscriptionRequestPayload.getName());
						userPlan.setPlanType(subscriptionRequestPayload.getPlanType());
						userPlan.setProductId(subscriptionRequestPayload.getProductId());
						userPlan.setPrice(subscriptionRequestPayload.getPrice());
						userPlan.setDiscount(subscriptionRequestPayload.getDiscount());
						userPlan.setUpdateDate(new Date());
						if (!subscriptionRequestPayload.getDescriptionRequestPayloadList().isEmpty()) {
							for (PlanDescriptionRequestPayload descriptionRequestPayload : subscriptionRequestPayload
									.getDescriptionRequestPayloadList()) {
								PlanDescription planDescription = new PlanDescription();
								PlanDescriptionResponse descriptionResponse = new PlanDescriptionResponse();
								if (descriptionRequestPayload.getDescriptionId() > 0) {
									PlanDescription oldPlanDes = planDescriptionRepository.findDescription(
											descriptionRequestPayload.getDescriptionId(), userPlan.getId());
									if (oldPlanDes != null) {
										oldPlanDes.setDescription(descriptionRequestPayload.getDescription());
										oldPlanDes.setTitle(descriptionRequestPayload.getTitle());
										oldPlanDes.setImage(descriptionRequestPayload.getImage());
										planDescriptionRepository.save(oldPlanDes);
										BeanUtils.copyProperties(oldPlanDes, descriptionResponse);
										list.add(descriptionResponse);
										log.info(" Plan description data updated seccessfully !!" + oldPlanDes);
									} else {
										log.info("Description id not found in our database !!"
												+ descriptionRequestPayload.getDescriptionId());
									}
								} else {
									planDescription.setDescription(descriptionRequestPayload.getDescription());
									planDescription.setTitle(descriptionRequestPayload.getTitle());
									planDescription.setImage(descriptionRequestPayload.getImage());
									planDescriptionRepository.save(planDescription);
									BeanUtils.copyProperties(planDescription, descriptionResponse);
									list.add(descriptionResponse);
									log.info(" Plan description data saved seccessfully !!" + planDescription);
								}
							}
						}
						userPlanRepository.save(userPlan);
						BeanUtils.copyProperties(userPlan, userPlanResponse);
						userPlanResponse.setDescriptionResponseList(list);
						log.info("User plan data updated successfully");
						response.put(Constant.RESPONSE_CODE, Constant.OK);
						response.put(Constant.MESSAGE, Constant.UPDATED_SUCCESSFULLY);
						response.put(Constant.DATA, userPlanResponse);
					} else {
						response.put(Constant.RESPONSE_CODE, Constant.NOT_FOUND);
						response.put(Constant.MESSAGE, Constant.ID_NOT_FOUND_MESSAGE);
						response.put(Constant.DATA, subscriptionRequestPayload.getId());
					}
				} catch (Exception e) {
					log.error("Unable to add new Subscription");
					e.printStackTrace();
					response.put(Constant.RESPONSE_CODE, Constant.SERVER_ERROR);
					response.put(Constant.MESSAGE, Constant.SERVER_MESSAGE);
					response.put(Constant.DATA, subscriptionRequestPayload);
				}
			} else {
				/* ........add subscription here......... */
				try {
					UserPlan userPlan = new UserPlan();
					userPlan.setStatus(Constant.ONE);
					userPlan.setName(subscriptionRequestPayload.getName());
					userPlan.setProductId(subscriptionRequestPayload.getProductId());
					userPlan.setPrice(subscriptionRequestPayload.getPrice());
					userPlan.setDiscount(subscriptionRequestPayload.getDiscount());
					userPlan.setPlanType(subscriptionRequestPayload.getPlanType());
					userPlan.setCreationDate(new Date());
					userPlan = userPlanRepository.save(userPlan);
					BeanUtils.copyProperties(userPlan, userPlanResponse);
					log.info("Plan saved successfully");
					for (PlanDescriptionRequestPayload descriptionRequestPayload : subscriptionRequestPayload
							.getDescriptionRequestPayloadList()) {
						PlanDescription planDescription = new PlanDescription();
						/* ----- new plan description add here ----- */
						PlanDescriptionResponse planDescriptionResponse = new PlanDescriptionResponse();
						planDescription.setDescription(descriptionRequestPayload.getDescription());
						planDescription.setTitle(descriptionRequestPayload.getTitle());
						planDescription.setImage(descriptionRequestPayload.getImage());
						planDescription.setUserPlan(userPlan);
						planDescriptionRepository.save(planDescription);
						BeanUtils.copyProperties(planDescription, planDescriptionResponse);
						list.add(planDescriptionResponse);
						log.info("new Plan description data saved seccussfully !!" + planDescription);
					}
					userPlanResponse.setDescriptionResponseList(list);
					response.put(Constant.RESPONSE_CODE, Constant.OK);
					response.put(Constant.MESSAGE, Constant.SAVED_SUCCESSFULLY);
					response.put(Constant.DATA, userPlanResponse);
				} catch (Exception e) {
					log.error("Unable to add new Subscription");
					e.printStackTrace();
					response.put(Constant.RESPONSE_CODE, Constant.BAD_REQUEST);
					response.put(Constant.MESSAGE, Constant.BAD_REQUEST_MESSAGE);
				}
			}
		} catch (Exception e1) {
			response.put(Constant.HTTP_STATUS, Constant.SERVER_ERROR);
			response.put(Constant.MESSAGE, Constant.SERVER_MESSAGE);
			log.error("Something went wrong to fetch out list");
			e1.printStackTrace();
		}
		return response;
	}

	@Override
	public Map<String, Object> getAllSubscriptionPlan() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<UserPlanResponse> planResponseList = new ArrayList<UserPlanResponse>();
			List<UserPlan> planList = userPlanRepository.findAllPlan();
			if (!planList.isEmpty()) {
				log.info("plan list found successfully !! " + planList);
				for (UserPlan userPlan : planList) {
					List<PlanDescriptionResponse> planDescriptionResponseList = new ArrayList<PlanDescriptionResponse>();
					UserPlanResponse planResponse = new UserPlanResponse();
					BeanUtils.copyProperties(userPlan, planResponse);
					List<PlanDescription> descriptionResponseList = planDescriptionRepository
							.findByAllDescriptionByPlanId(userPlan.getId());
					if (!descriptionResponseList.isEmpty()) {
						for (PlanDescription planDescriptionResponse : descriptionResponseList) {
							PlanDescriptionResponse descriptionResponse = new PlanDescriptionResponse();
							BeanUtils.copyProperties(planDescriptionResponse, descriptionResponse);
							planDescriptionResponseList.add(descriptionResponse);
						}
					} else {
						log.info("Description Plan not found into the database! status - {}", Constant.NOT_FOUND);
					}
					planResponse.setDescriptionResponseList(planDescriptionResponseList);
					planResponseList.add(planResponse);
				}
				response.put(Constant.RESPONSE_CODE, Constant.OK);
				response.put(Constant.MESSAGE, Constant.RECORD_FOUND);
				response.put(Constant.DATA, planResponseList);
				log.info("PlanResponse list data found into the database! status - {}", Constant.OK);
			} else {
				response.put(Constant.RESPONSE_CODE, Constant.OK);
				response.put(Constant.MESSAGE, Constant.NOT_FOUND_MESSAGE);
				response.put(Constant.DATA, planList);
				log.info("Plan not found into the database! status - {}", Constant.NOT_FOUND);
			}

		} catch (Exception e) {
			log.error("Something went wrong to fetch out list");
			response.put(Constant.HTTP_STATUS, Constant.SERVER_ERROR);
			response.put(Constant.MESSAGE, Constant.SERVER_MESSAGE);
		}
		return response;
	}

	@Override
	public Map<String, Object> deleteSubscription(Long planId) {
		Map<String, Object> map = new HashMap<>();
		try {
			if (planId != null) {
				UserPlan oldSubscription = userPlanRepository.findByIdAndStatus(planId, Constant.ONE);
				if (oldSubscription != null) {
					if (oldSubscription.getStatus() == 0) {
						map.put(Constant.RESPONSE_CODE, Constant.BAD_REQUEST);
						map.put(Constant.MESSAGE, Constant.ALREADY_DELETED_MESSAGE);
						log.info("Already deleted! status - {}", Constant.BAD_REQUEST);
					} else {
						/*----- Perform delete operation -----*/
						oldSubscription.setStatus(Constant.ZERO);
						oldSubscription.setUpdateDate(new Date());
						oldSubscription = userPlanRepository.save(oldSubscription);
						map.put(Constant.RESPONSE_CODE, Constant.OK);
						map.put(Constant.MESSAGE, Constant.DELETED_MESSAGE);
						log.info("Deleted successfully! status - {}", Constant.OK);
					}
				} else {
					map.put(Constant.RESPONSE_CODE, Constant.NOT_FOUND);
					map.put(Constant.MESSAGE, Constant.ID_NOT_FOUND_MESSAGE);
					log.info("Given code not found into the database! status - {}", Constant.NOT_FOUND);
				}
			} else {
				map.put(Constant.RESPONSE_CODE, Constant.BAD_REQUEST);
				map.put(Constant.MESSAGE, Constant.ID_CAN_NOT_NULL_MESSAGE);
				log.info("Id can not null, it should be valid! status - {}", Constant.BAD_REQUEST);
			}
		} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
			map.put(Constant.RESPONSE_CODE, Constant.DB_CONNECTION_ERROR);
			map.put(Constant.MESSAGE, Constant.NO_SERVER_CONNECTION);
			log.error("Exception : " + e.getMessage());
		} catch (Exception e) {
			map.put(Constant.RESPONSE_CODE, Constant.SERVER_ERROR);
			map.put(Constant.MESSAGE, Constant.SERVER_MESSAGE);
			log.error("Exception : " + e.getMessage());
		}
		return map;
	}

	@Override
	public Map<String, Object> subscribe(SubscribeRequstPayload subscribeRequstPayload) {
		Map<String, Object> map = new HashMap<>();
		try {
			UserSubscription subscription = userSubscriptionRepository.findByUser(subscribeRequstPayload.getUserId());
			if (subscription != null) {
				/* ----- plan cancel (one => subscribe )----- */
				if (subscribeRequstPayload.getStatus().equals(Constant.ONE)) {
					subscription.setIsSubscriptionActive(Constant.ONE);
					subscription.setUpdationDate(new Date());
					userSubscriptionRepository.save(subscription);
					map.put(Constant.RESPONSE_CODE, Constant.OK);
					map.put(Constant.MESSAGE, "Plan subscribe successfully !!");
					log.info("Plan subscribe successfully !!" + subscription);
					/* ----- plan expire (two => expire) ----- */
				} else if (subscribeRequstPayload.getStatus().equals(Constant.TWO)) {
					subscription.setIsSubscriptionActive(Constant.ZERO);
					subscription.setUpdationDate(new Date());
					userSubscriptionRepository.save(subscription);
					map.put(Constant.RESPONSE_CODE, Constant.OK);
					map.put(Constant.MESSAGE, "Plan cancel successfully !!");
					log.info("Plan cancel successfully !!" + subscription);
					/* ----- plan expire (three => expire) ----- */
				} else if (subscribeRequstPayload.getStatus().equals(Constant.THREE)) {
					subscription.setIsSubscriptionActive(Constant.ZERO);
					subscription.setUpdationDate(new Date());
					userSubscriptionRepository.save(subscription);
					map.put(Constant.RESPONSE_CODE, Constant.OK);
					map.put(Constant.MESSAGE, "Plan expired !!");
					log.info("Plan expired !!" + subscription);
				}
			} else {
				UserPlan userPlan = userPlanRepository.findByIdAndStatus(subscribeRequstPayload.getPlanId(),
						Constant.ONE);
				if (userPlan != null) {
					/* ----- user subscribe ----- */
					if (subscribeRequstPayload.getStatus().equals(Constant.ONE)) {
						UserSubscription userSubscription = new UserSubscription();
						userSubscription.setUserPlan(userPlan);
						userSubscription.setSubscriptionDate(new Date());
						userSubscription.setIsSubscriptionActive(Constant.ONE);// user subscribe
						User user = userRepository.findByIdAndStatus(subscribeRequstPayload.getUserId(), Constant.ONE);
						if (user != null) {
							log.info("User found successfully !! " + user);
							userSubscription.setUser(user);
						} else {
							log.info("User not found !! " + user);
						}
						userSubscription = userSubscriptionRepository.save(userSubscription);
						map.put(Constant.RESPONSE_CODE, Constant.OK);
						map.put(Constant.MESSAGE, "User subscribe successfully !!");
						log.info("User subscribe successfully !! " + userSubscription);
					}
				} else {
					map.put(Constant.RESPONSE_CODE, Constant.OK);
					map.put(Constant.MESSAGE, Constant.RECORD_NOT_FOUND_MESSAGE);
					map.put(Constant.DATA, userPlan);
					log.info("plan not found in our database !!" + userPlan);
				}
			}
		} catch (Exception e) {
			map.put(Constant.RESPONSE_CODE, Constant.SERVER_ERROR);
			map.put(Constant.MESSAGE, Constant.SERVER_MESSAGE);
			log.error("Exception : " + e.getMessage());
		}
		return map;
	}

	@Override
	public Map<String, Object> checkPlanExpire(Long userId) {
		Map<String, Object> map = new HashMap<>();
		UserSubscribeResponse userSubscribeResponse = new UserSubscribeResponse();
		try {
			if (userId != null) {
				UserSubscription usersubcribe = userSubscriptionRepository.findByUser(userId);
				if (usersubcribe != null) {
					userSubscribeResponse.setIsSubscriptionActive(usersubcribe.getIsSubscriptionActive());
					userSubscribeResponse.setUserPlan(usersubcribe.getUserPlan());
				} else {
					userSubscribeResponse.setIsSubscriptionActive(Constant.ZERO);
					userSubscribeResponse.setUserPlan(null);
				}
				map.put(Constant.RESPONSE_CODE, Constant.OK);
				map.put(Constant.MESSAGE, Constant.DATA_FOUND_MESSAGE);
				map.put(Constant.DATA, userSubscribeResponse);
				log.info("Data found successfully !!" + userSubscribeResponse);
			} else {
				map.put(Constant.RESPONSE_CODE, Constant.BAD_REQUEST);
				map.put(Constant.MESSAGE, Constant.ID_CAN_NOT_NULL_MESSAGE);
				log.info(Constant.ID_CAN_NOT_NULL_MESSAGE, Constant.BAD_REQUEST);
			}
		} catch (Exception e) {
			map.put(Constant.RESPONSE_CODE, Constant.SERVER_ERROR);
			map.put(Constant.MESSAGE, "Error updating profile image");
			log.error("Error updating profile image for userId: {}", e);
		}
		return map;
	}

	@Override
	public Map<String, Object> blockUnblockPlan(Long planId, Short status) {
		Map<String, Object> response = new HashMap<>();
		Optional<UserPlan> plan = null;
		UserPlan userPlan = new UserPlan();
		try {
			plan = userPlanRepository.findById(planId);
			if (plan.isPresent()) {
				userPlan = plan.get();
				/* ---------------Here user delete can't block & unblock ------------ */
				if (!(userPlan.getStatus().equals(Constant.ZERO))) {
					/* ---------------Here unblock the user------------ */
					log.info("user found status - {}", userPlan);
					if (status.equals(Constant.ONE)) {
						if (userPlan.getStatus().equals(Constant.ONE)) {
							response.put(Constant.HTTP_STATUS, Constant.CONFLICT);
							response.put(Constant.MESSAGE, Constant.ALREADY_UNBLOCKED_MESSAGE);
							log.info("Already unblocked! status - {}", Constant.CONFLICT);
							return response;
						} else {
							userPlan.setStatus(status);
							userPlan.setUpdateDate(new Date());
							userPlan = userPlanRepository.save(userPlan);
							response.put(Constant.HTTP_STATUS, Constant.OK);
							response.put(Constant.MESSAGE, Constant.UNBLOCKED_SUCCESS_MESSAGE);
							log.info("unblocked successfully! status - {}", Constant.OK);
						}
						/* ---------------Here block the user------------ */
					} else if (status.equals(Constant.TWO)) {
						if (userPlan.getStatus().equals(Constant.TWO)) {
							response.put(Constant.HTTP_STATUS, Constant.CONFLICT);
							response.put(Constant.MESSAGE, Constant.ALREADY_BLOCKED_MESSAGE);
							log.info("Already unblocked! status - {}", Constant.CONFLICT);
							return response;
						} else {
							userPlan.setStatus(status);
							userPlan.setUpdateDate(new Date());
							userPlan = userPlanRepository.save(userPlan);
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
					response.put(Constant.MESSAGE, Constant.PLAN_DELETED_CANT_BLOCK_UNBLOCK);
					log.info("Plan Already Deleted ! status - {}", Constant.CONFLICT);
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
			log.error("Error to find user by mobile ", userPlan);
			response.put(Constant.HTTP_STATUS, HttpStatus.BAD_REQUEST);
			response.put(Constant.MESSAGE, Constant.PLEASE_TRY_AGAIN);
		}
		return response;
	}

	@Override
	public Map<String, Object> updateProfileImage(MultipartFile image) {
		Map<String, Object> map = new HashMap<>();
		try {
			// Check if the profile_image is not null
			if (image != null && !image.isEmpty()) {
				// Upload the new profile image and set the image URL
				String imagePath = logourl + FileUploader.uploadFile(image, logodir, System.currentTimeMillis());
				log.info("Image update successful! Image path: {}", imagePath);
				// Save the updated user object
				map.put(Constant.RESPONSE_CODE, Constant.OK);
				map.put(Constant.MESSAGE, Constant.PROFILE_UPDATED_MESSAGE);
				map.put(Constant.DATA, imagePath); // Including userResponse instead of portfolioResponsesList
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
			log.error("Error updating profile image for userId: {}", e);
		}
		return map;
	}

	@Override
	public Map<String, Object> getProductList() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Product> productList = productRepository.findAllProductList();
			if (!productList.isEmpty()) {
				map.put(Constant.RESPONSE_CODE, Constant.OK);
				map.put(Constant.MESSAGE, Constant.RECORD_FOUND);
				map.put(Constant.DATA, productList);
				log.info(Constant.RECORD_FOUND + " successfully !! - status { }", Constant.OK);
			} else {
				map.put(Constant.HTTP_STATUS, Constant.OK);
				map.put(Constant.MESSAGE, Constant.RECORD_NOT_FOUND_MESSAGE);
				log.info("Record not found! status - {}", Constant.OK);
			}
		} catch (Exception e) {
			log.error("Error to find user by mobile ", e.getMessage());
			map.put(Constant.HTTP_STATUS, HttpStatus.BAD_REQUEST);
			map.put(Constant.MESSAGE, Constant.PLEASE_TRY_AGAIN);
		}
		return map;
	}

	@Override
	public Map<String, Object> getPlanTypeList() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<PlanType> planList = planTypeRepository.findAllPlanTypeList();
			if (!planList.isEmpty()) {
				map.put(Constant.RESPONSE_CODE, Constant.OK);
				map.put(Constant.MESSAGE, Constant.RECORD_FOUND);
				map.put(Constant.DATA, planList);
				log.info(Constant.RECORD_FOUND + " successfully !! - status { }", Constant.OK);
			} else {
				map.put(Constant.HTTP_STATUS, Constant.OK);
				map.put(Constant.MESSAGE, Constant.RECORD_NOT_FOUND_MESSAGE);
				log.info("Record not found! status - {}", Constant.OK);
			}
		} catch (Exception e) {
			log.error("Error to find user by mobile ", e.getMessage());
			map.put(Constant.HTTP_STATUS, HttpStatus.BAD_REQUEST);
			map.put(Constant.MESSAGE, Constant.PLEASE_TRY_AGAIN);
		}
		return map;
	}
}
