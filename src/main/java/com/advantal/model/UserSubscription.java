package com.advantal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	private Long userId;

	private Date subscriptionDate;

	private Date updationDate;

	@OneToOne(targetEntity = UserPlan.class)
	@JoinColumn(name = "userPlan_id_fk", referencedColumnName = "id")
	private UserPlan userPlan;

	private Short isSubscriptionActive;

	@OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "userIdFk", referencedColumnName = "id")
	private User user;
}
