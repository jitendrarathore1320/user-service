package com.advantal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.advantal.model.UserPlan;
@Repository
public interface UserPlanRepository extends JpaRepository<UserPlan, Long>{

	@Query(value = "SELECT * FROM user_plan plan WHERE plan.status!=0 ", nativeQuery = true)
	List<UserPlan> findAllPlan();

	UserPlan findByIdAndProductIdAndStatus(Long id, String productId, Short one);

	UserPlan findByIdAndStatus(Long planId, Short one);

}
