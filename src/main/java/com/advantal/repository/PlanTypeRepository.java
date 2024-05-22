package com.advantal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.advantal.model.PlanType;

@Repository
public interface PlanTypeRepository extends JpaRepository<PlanType, Long>{

	@Query(value = "SELECT * FROM plan_type plan WHERE plan.status!=0 ", nativeQuery = true)
	List<PlanType> findAllPlanTypeList();

}
