package com.advantal.requestPayload;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionRequestPayload {

	@NotNull(message = "Plan Id can't be null !!")
	private Long id;

	@NotEmpty(message = "Name can't be enpty !!")
	@NotNull(message = "Name can't be null !!")
	private String name;

	@NotEmpty(message = "ProductId can't be enpty !!")
	@NotNull(message = "ProductId can't be null !!")
	private String productId;
	
	@NotNull(message = "Price can't be null !!")
	private Double price;
	
	@NotNull(message = "Discount can't be null !!")
	private Integer discount;
	
	@NotEmpty(message = "PlanType can't be enpty !!")
	@NotNull(message = "PlanType can't be null !!")
	private String planType;

	@Valid
	private List<PlanDescriptionRequestPayload> descriptionRequestPayloadList;

}
