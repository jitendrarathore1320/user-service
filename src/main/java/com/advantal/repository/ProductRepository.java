package com.advantal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.advantal.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query(value = "SELECT * FROM product pro WHERE pro.status!=0 ", nativeQuery = true)
	List<Product> findAllProductList();

}
