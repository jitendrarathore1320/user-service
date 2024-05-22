package com.advantal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.advantal.model.PlanDescription;

@Repository
public interface PlanDescriptionRepository extends JpaRepository<PlanDescription, Long> {

	@Query(value = "SELECT * FROM plan_description us WHERE us.user_plan_id_fk = ?1 ", countQuery = "SELECT count(*) from plan_description us WHERE us.user_plan_id_fk = ?1", nativeQuery = true)
	List<PlanDescription> findByAllDescriptionByPlanId(Long id);

	@Query(value = "SELECT * FROM plan_description us WHERE us.id = ?1 And us.user_plan_id_fk = ?2 ", nativeQuery = true)
	PlanDescription findDescription(Long descriptionId, Long planId);

}
