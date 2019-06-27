package com.example.demo.d1.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class EncryptMessage {
	private static String MD5_KEY = "MD5";
	private static String SHA_KEY = "SHA";
	private static String key = "support";
	public static String encryptByMD5(String str) {
		String result = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance(MD5_KEY);
			byte[] bytes = md5.digest(str.getBytes("UTF-8"));
			result = new String(bytes,"UTF-8");
			result = key + result + key;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return  result;
	}
	
	public static String encryptBySHA(String str) {
		String result = null;
		try {
			MessageDigest sha = MessageDigest.getInstance(SHA_KEY);
			byte[] bytes = sha.digest(str.getBytes("UTF-8"));
			result = new String(bytes,"UTF-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String encryptPassword(String str){
		String firstEncrypt = encryptByMD5(str);
		String secondEncrypt = encryptBySHA(firstEncrypt);
		try {
			return Base64.encode(secondEncrypt.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
