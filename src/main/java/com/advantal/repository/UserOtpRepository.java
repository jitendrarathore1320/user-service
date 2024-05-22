package com.advantal.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advantal.model.Otp;
@Transactional
@Repository
public interface UserOtpRepository extends JpaRepository<Otp, Long>{


//	@Query(value ="SELECT * FROM user us WHERE us.status!=0 ",nativeQuery = true)
//	@Query(value = "Select * from user_otp",nativeQuery = true)
	Otp findByUserIdFk(Long user_id);

	void deleteByUserIdFk(Long user_id);

}
