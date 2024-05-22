package com.advantal.requestPayload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserProfileRequestPayload {

	@NotEmpty(message = "PhoneNo name can't be empty !!")
	private String phoneNo;

	@NotEmpty(message = "CountryCode name can't be empty !!")
	private String countryCode;

	@NotEmpty(message = "Name name can't be empty !!")
	private String name;
	
	@NotEmpty(message = "Email can't be empty !!")
	private String email;

	@NotNull(message = "userId can't be null !!")
	private Long userId;
}
