package com.advantal.servicesImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advantal.model.Otp;
import com.advantal.model.User;
import com.advantal.repository.UserOtpRepository;
import com.advantal.utils.Constant;
import com.advantal.utils.RandomStringGenerator;

@Service
public class UtilityServiceImpl {

	@Autowired
	private UserOtpRepository userOtpRepository;

	public String sendOtp(User user) {
		String str = "";
		Otp oldotp = new Otp();
		Otp newotp = new Otp();
		Map<String, Object> map = new HashMap<>();
		try {
			String otpstr = RandomStringGenerator.getRandomNumberString(4);
			// encrypt OTP first
//				otpstr = EncryptDecryptUtil.encrypt(otpstr);
			// save otp
			oldotp = userOtpRepository.findByUserIdFk(user.getId());
			if (oldotp != null) {
				oldotp.setOtp(otpstr);
				oldotp.setCreationDate(new Date());
				oldotp.setStatus(Constant.ZERO);
				oldotp = userOtpRepository.save(oldotp);
				str = oldotp.getOtp();
			} else {
				newotp.setUserIdFk(user.getId());
				newotp.setOtp(otpstr);
				newotp.setCreationDate(new Date());
				newotp.setStatus(Constant.ZERO);
				newotp = userOtpRepository.save(newotp);
				str = newotp.getOtp();
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Constant.HTTP_STATUS, Constant.SERVER_ERROR);
			map.put(Constant.MESSAGE, Constant.PLEASE_TRY_AGAIN);
		}
		return str;
	}
}
