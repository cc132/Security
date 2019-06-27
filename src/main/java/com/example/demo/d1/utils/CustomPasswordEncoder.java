package com.example.demo.d1.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder{

	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		return EncryptMessage.encryptPassword(rawPassword.toString()).equals(encodedPassword);
	}

//	@Override
//	public String encode(CharSequence rawPassword) {
//		// TODO Auto-generated method stub
//		return EncryptMessage.encryptPassword(rawPassword.toString());
//	}
//
//	@Override
//	public boolean matches(CharSequence rawPassword, String encodedPassword) {
//		// TODO Auto-generated method stub
//		return EncryptMessage.encryptPassword(rawPassword.toString()).equals(encodedPassword);
//	}
//	
}
