package com.advantal.responsePayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogResponse {

	private Long id;

	private String lastLoginAt;

	private String email;

	private String phoneNo;
	
	private String deviceType;

}
