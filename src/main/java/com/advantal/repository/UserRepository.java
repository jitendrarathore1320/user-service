package com.advantal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.advantal.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM user us WHERE us.status!=0 and us.device_id=?", countQuery = "SELECT count(*) from user us WHERE us.status != 0 and us.device_id=?", nativeQuery = true)
	User findDeviceId(String deviceId);

	Optional<User> findByDeviceToken(String deviceToken);

	User findByPhoneNo(String phoneNo);

	User findByImageUrl(String ImageUrl);

	User findByDeviceIdOrDeviceToken(String deviceId, String deviceToken);

	@Query(value = "SELECT * FROM user us WHERE us.status!=0 and (us.name like concat('%',?1,'%') or us.device_id like concat('%',?1,'%') or us.device_token like concat('%',?1,'%') or us.device_type like concat('%',?1,'%') or us.phone_no like concat('%',?1,'%')) ORDER BY us.id DESC", nativeQuery = true)
	Page<User> findAllUser(String keyword, Pageable pageable);

	@Query(value = "SELECT * FROM user us WHERE us.status!=0 ORDER BY us.id DESC", countQuery = "SELECT count(*) from user us WHERE us.status != 0 ORDER BY us.id DESC", nativeQuery = true)
	Page<User> findAllUsers(Pageable pageable);

	@Query(value = "SELECT * FROM user us WHERE us.status!=0 and us.phone_no=? and us.country_code=?", nativeQuery = true)
	User findUser(String phoneNo, String countryCode);

	User findByPhoneNoAndStatus(String phoneNo, Short status);

	User findByPhoneNoAndCountryCode(String phoneNo, String countryCode);

	User findByIdAndStatus(Long userId, Short status);

	@Query(value = "SELECT COUNT(*) AS user_count FROM user;", nativeQuery = true)
	Long findAllUsersCount();

	@Query(value = "SELECT COUNT(*) AS user_count FROM user us WHERE us.status=1;", nativeQuery = true)
	Long findAllActiveUsersCount();

	@Query(value = "SELECT COUNT(*) AS user_count FROM user us WHERE us.status=2;", nativeQuery = true)
	Long findAllInactiveUsersCount();

	@Query(value = "SELECT COUNT(*) AS user_count FROM user us WHERE us.status=0;", nativeQuery = true)
	Long findAllDeletedUsersCount();

	@Query(value = "SELECT COUNT(*) AS user_count FROM user us WHERE us.status=3;", nativeQuery = true)
	Long findAllNotVerifiedUSersCount();

//	SELECT COUNT(*) AS record_count
//	FROM amwal_db.user
//	WHERE creation_date >= '2023-07-01' AND creation_date <= '2023-07-30';

	@Query(value = "SELECT COUNT(*) AS record_count	FROM user us WHERE (us.creation_date >= ?1 AND us.creation_date <= ?2);", nativeQuery = true)
	Long findAllUserCurrentMonth(String startingDate, String endingDate);

	@Query(value = "SELECT COUNT(*) AS record_count	FROM user us WHERE (us.creation_date >= ?1 AND us.creation_date <= ?2) AND us.status=1;", nativeQuery = true)
	Long findAllCurrentMonthActiveUsersCount(String string, String string2);

	@Query(value = "SELECT * FROM user us WHERE us.status!=0 and (us.name like concat('%',?1,'%') or us.device_id like concat('%',?1,'%') or us.device_token like concat('%',?1,'%') or us.device_type like concat('%',?1,'%') or us.phone_no like concat('%',?1,'%')) ORDER BY us.id DESC", nativeQuery = true)
	List<User> findAllUserWithSearching(String keyword);

	@Query(value = "SELECT * FROM user us WHERE us.status!=0 ORDER BY us.id DESC", countQuery = "SELECT count(*) from user us WHERE us.status != 0 ORDER BY us.id DESC", nativeQuery = true)
	List<User> findAllUsersWithoutSearching();

	@Query(value = "SELECT COUNT(*) AS user_count FROM user us WHERE DATE(us.creation_date) = ?", nativeQuery = true)
	Long findAllUserCurrentDate(String startDate);

	@Query(value = "SELECT COUNT(*) AS user_count FROM user us WHERE (DATE(us.creation_date) = ? AND us.status=1 )", nativeQuery = true)
	Long findAllCurrentDayActiveUsersCount(String startDate);

	@Query(value = "SELECT COUNT(*) AS count FROM user us WHERE YEAR(us.creation_date) = ?", nativeQuery = true)
	Long findAllUserCurrentYear(String startDate);

	@Query(value = "SELECT COUNT(*) AS count FROM user us WHERE (YEAR(us.creation_date) = ? AND us.status=1)", nativeQuery = true)
	Long findAllUserCurrentYearActiveUsersCount(String startDate);

	@Query(value = "SELECT * FROM user us WHERE us.status!=0 and us.id=?", countQuery = "SELECT count(*) from user us WHERE us.status != 0 and us.id=?", nativeQuery = true)
	User findByUserId(Long userIdFk);

	// User findById(Long userId);

//	User findById(String deviceId);

}
