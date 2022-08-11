package com.example.moo.config;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
public class PasswordEncoderWrapperTest {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	PasswordEncoderWrapper passwordEncoderWrapper = new PasswordEncoderWrapper(passwordEncoder);
	
	@Test 
	void Encode () {
		
		System.out.println(passwordEncoderWrapper.returnHi());
		String rawPassword = "1233456789";
		
		String encodedPassword = passwordEncoderWrapper.encode(rawPassword);
		
		assertTrue(passwordEncoderWrapper.matches(rawPassword, encodedPassword));
	}
}
