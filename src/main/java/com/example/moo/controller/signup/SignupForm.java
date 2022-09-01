package com.example.moo.controller.signup;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupForm {
	@NotEmpty (message = "name is necessay.")
	private String name;
	@NotEmpty (message = "password is necessary.")
	private String password;	
}

