package com.advantal.requestPayload;

import com.advantal.model.UserPlan;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestPayload {

	private String phoneNo;
	
	private String deviceId;  

	private String deviceToken;

	private String deviceType;
	
	private String countryCode;
	
	@JsonIgnore
	private UserPlan subscriptionId;

}
