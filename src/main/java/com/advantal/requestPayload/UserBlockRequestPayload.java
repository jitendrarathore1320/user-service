package com.advantal.requestPayload;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBlockRequestPayload {

	@NotNull(message = "id can't be null !!")
	private Long id;
	
	@NotNull(message = "Status can't be null !!")
	private Short status;
	
}

