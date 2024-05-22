package com.advantal.requestPayload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyRequestPayload {
	
	@NotEmpty(message = "Country code can't be empty !!")
	@NotNull(message = "Country code can't be null !!")
	private String countryCode;
	
	@NotEmpty(message = "Phone number can't be empty !!")
	@NotNull(message = "Phone number can't be null !!")
	private String phoneNo;
	
	@NotEmpty(message = "OTP can't be empty !!")
	@NotNull(message = "OTP can't be null !!")
	private String otp;

}
