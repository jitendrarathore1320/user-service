package com.advantal.responsePayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDescriptionResponse {

	private Long id;

	private String image;

	private String title;

	private String description;
}
