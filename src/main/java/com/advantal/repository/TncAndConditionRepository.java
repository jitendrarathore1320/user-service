package com.advantal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advantal.model.TncAndPolicy;

@Repository
public interface TncAndConditionRepository extends JpaRepository<TncAndPolicy, Integer>{

	TncAndPolicy findByName(String name);

}
