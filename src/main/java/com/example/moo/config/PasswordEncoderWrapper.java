package com.example.moo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderWrapper {
	
	private PasswordEncoder passwordEncoder;

	public PasswordEncoderWrapper (PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	String encode(CharSequence rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
	
	boolean matches(CharSequence rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
	
	public String returnHi() {
		return "HI";
	}

}
