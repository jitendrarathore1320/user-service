package com.advantal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.advantal.model.UserLogs;

@Repository
public interface UserLogsRepository extends JpaRepository<UserLogs, Long> {

	@Query(value = "SELECT * FROM user_logs us  ORDER BY us.id DESC", countQuery = "SELECT count(*) from user_logs us ORDER BY us.id DESC", nativeQuery = true)
	Page<UserLogs> findAllUserLongWithOutSearching(Pageable pageable);

	@Query(value = "SELECT * FROM user_logs us WHERE us.last_login_at like concat('%',?1,'%') ORDER BY us.id DESC", nativeQuery = true)
	Page<UserLogs> findAllUserLongWithSearching(String keyword, Pageable pageable);

	@Query(value = "SELECT * FROM user_logs us WHERE us.last_login_at like concat('%',?1,'%') And (us.last_login_at >= ?2 AND us.last_login_at <= ?3) ORDER BY us.id DESC", nativeQuery = true)
	Page<UserLogs> findAllUserLongWithSearchingAndFilter(String keyword, String startDate, String endDate,
			Pageable pageable);

	@Query(value = "SELECT * FROM user_logs us WHERE  (us.last_login_at >= ?1 AND us.last_login_at <= ?2)  ORDER BY us.id DESC", countQuery = "SELECT count(*) FROM user_logs us WHERE  (us.last_login_at >= ?1 AND us.last_login_at <= ?2) ORDER BY us.id DESC", nativeQuery = true)
	Page<UserLogs> findAllUserLongWithOutSearchingWithFilter(String startDate, String endDate, Pageable pageable);

}
