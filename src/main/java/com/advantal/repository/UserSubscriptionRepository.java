package com.advantal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.advantal.model.UserSubscription;
@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long>{

	@Query(value = "SELECT * FROM user_subscription sub WHERE sub.user_id_fk = ?1", nativeQuery = true)
	UserSubscription findByUser(Long userId);

}
