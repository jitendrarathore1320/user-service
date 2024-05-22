package com.advantal.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
public class PlanDescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String image;

	private String title;

	private String description;

	@OneToOne(targetEntity = UserPlan.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "userPlan_id_fk", referencedColumnName = "id")
	private UserPlan userPlan;
}
