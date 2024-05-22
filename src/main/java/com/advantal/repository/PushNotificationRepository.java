package com.advantal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advantal.model.PushNotification;

public interface PushNotificationRepository extends JpaRepository<PushNotification, Long>{

	List<PushNotification> findByUserId(Long userId);

}
