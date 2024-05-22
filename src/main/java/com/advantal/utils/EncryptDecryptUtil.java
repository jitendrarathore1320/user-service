package com.advantal.utils;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class EncryptDecryptUtil {

	private static final String ALGO = "AES";
	private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't', 'A', 'a', 'r', 't', 'e', 'k',
			'K', 'e', 'y' };

	/**
	 * Encrypt password
	 * 
	 * @param Data
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
//		String encryptedValue = new BASE64Encoder().encode(encVal);
		String encryptedValue = Base64.getEncoder().encodeToString(encVal);
		return encryptedValue;
	}

	/**
	 * Decrypt password
	 * 
	 * @param encryptedData
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
//		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);

		byte[] decordedValue = Base64.getDecoder().decode(encryptedData);

		byte[] decValue = c.doFinal(decordedValue);
		
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}


}
