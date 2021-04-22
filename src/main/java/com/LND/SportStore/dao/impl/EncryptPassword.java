package com.LND.SportStore.dao.impl;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncryptPassword {

	public static String encrytePassword(String password) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder.encode(password);
    }
	
	
	public static void main(String[] args) {
		new EncryptPassword();
		System.out.println(EncryptPassword.encrytePassword("8120"));
	}
}
