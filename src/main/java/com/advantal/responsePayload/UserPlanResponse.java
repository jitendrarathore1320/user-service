package com.advantal.responsePayload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPlanResponse {
	
	private Long id;

	private String name;
	
	private String productId;
	
	private Double price;
	
	private Integer discount;
	
	private String planType;

	private Short status;

	private List<PlanDescriptionResponse> descriptionResponseList;

}
