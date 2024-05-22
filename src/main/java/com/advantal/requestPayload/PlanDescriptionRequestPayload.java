package com.advantal.requestPayload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDescriptionRequestPayload {

	@NotNull(message = "Description Id can't be null !!")
	private Long descriptionId;

	@NotEmpty(message = "Title can't be empty !!")
	@NotNull(message = "Title can't be null !!")
	private String title;
	
//	@NotEmpty(message = "Image can't be empty !!")
	@NotNull(message = "Image can't be null !!")
	private String image;

	@NotEmpty(message = "Description can't be empty !!")
	@NotNull(message = "Description can't be null !!")
	private String description;
}
