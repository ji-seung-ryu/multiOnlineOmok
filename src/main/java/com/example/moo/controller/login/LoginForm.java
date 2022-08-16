package com.example.moo.controller.login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
@Getter
@Setter
public class LoginForm {
	@NotEmpty (message = "name is necessay.")
	private String name;
	@NotEmpty (message = "password is necessary.")
	private String password;	
}
