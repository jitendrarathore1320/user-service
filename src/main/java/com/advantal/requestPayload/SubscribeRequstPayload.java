package com.advantal.requestPayload;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeRequstPayload {

   @NotNull(message = "UserId can't be null !!")
   private Long userId;
   
   @NotNull(message = "PlanId can't be null !!")
   private Long planId;
   
   @NotNull(message = "Status can't be null !!")
   private Short status;
}
