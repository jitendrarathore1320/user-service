package com.advantal.requestPayload;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.advantal.model.UserPlan;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestPayload {
	
	@NotEmpty(message = "Phone number can't be empty !!")
	@NotNull(message = "Phone number can't be null !!")
	private String phoneNo;
	
	@NotEmpty(message = "Device id can't be empty !!")
	@NotNull(message = "Device id can't be null !!")
	private String deviceId;  

	@NotEmpty(message = "Device token can't be empty !!")
	@NotNull(message = "Device token can't be null !!")
	private String deviceToken;

	@NotEmpty(message = "Device type can't be empty !!")
	@NotNull(message = "Device type can't be null !!")
	private String deviceType;
	
	@NotEmpty(message = "Country code can't be empty !!")
	@NotNull(message = "Country code can't be null !!")
	private String countryCode;
	
//	@NotNull(message = "Status can't be null !!")
//	private Short status;
	
	@JsonIgnore
	private UserPlan subscriptionId;
		
}
