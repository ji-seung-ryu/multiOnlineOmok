package com.example.moo.config.encoder;

import org.springframework.stereotype.Component;

@Component
public interface PasswordEncoder {
	
	public String encode(CharSequence rawPassword);
	
	public boolean matches(CharSequence rawPassword, String encodedPassword);
}
