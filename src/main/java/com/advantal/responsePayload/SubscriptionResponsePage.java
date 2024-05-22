package com.advantal.responsePayload;

import java.util.List;

import com.advantal.model.UserPlan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SubscriptionResponsePage {

	private Integer pageIndex;

	private Integer pageSize;

	private Long totalElement;

	private Integer totalPages;

	private Boolean isLastPage;

	private Boolean isFirstPage;

	private List<UserPlan> subscriptionResponseList;
}
