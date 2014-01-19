package com.onlinetutoring.utills;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * Encription.java
 * 
 * @author Harshitha de Silva
 * 
 * @since Jan 2, 2014 1:57:04 AM
 */
public class Encription {
	/**
	 * 
	 * @param password
	 * @return
	 * Jan 13, 20142:38:27 PM
	 */
	public static String encript(String password) {

		StringBuffer hexString = new StringBuffer();
		MessageDigest md;

		try {
			md = MessageDigest.getInstance("MD5");

			md.update(password.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}

			System.out.println("Digest(in hex format):: " + sb.toString());

			// convert the byte to hex format method 2
			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			System.out.println("Digest(in hex format):: "
					+ hexString.toString());

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return hexString.toString();
	}
	/**
	 * 
	 * @param password
	 * @return
	 * Jan 13, 20142:38:31 PM
	 */
	public static String encriptPassword(String password) {

		StringBuffer hexString = new StringBuffer();

	//	String password = "123456";

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");

			md.update(password.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16) .substring(1));
			}

			System.out.println("Hex format : " + sb.toString());

			// convert the byte to hex format method 2

			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hex format : " + hexString.toString());
		
		return hexString.toString();
	}
}
