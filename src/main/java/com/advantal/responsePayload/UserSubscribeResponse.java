package com.advantal.responsePayload;

import com.advantal.model.UserPlan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubscribeResponse {

	private UserPlan userPlan;

	private Short isSubscriptionActive;
}
