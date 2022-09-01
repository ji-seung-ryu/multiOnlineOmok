package com.example.moo.config;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.moo.config.encoder.PasswordEncoder;



@SpringBootTest
public class PasswordEncoderWrapperTest {
	
	@Autowired
	private PasswordEncoder passwordEncoderWrapper;
	
	@Test 
	void Encode () {
		String rawPassword = "1233456789";
		
		String encodedPassword = passwordEncoderWrapper.encode(rawPassword);
		
		assertTrue(passwordEncoderWrapper.matches(rawPassword, encodedPassword));
	}
}
