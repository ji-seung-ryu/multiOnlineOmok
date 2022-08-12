package com.example.moo.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.moo.service.Member;
import com.example.moo.service.MemberService;

@Controller
public class LoginController {
	
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	/*private final CheckValidity checkValidity;
	
	public LoginController (CheckValidity checkValidity) {
		this.checkValidity = checkValidity; 
	}
	*/
    
	@GetMapping("/login")
	public @ResponseBody String returnLoginPage () {
		return "LoginPage";
	}
	
	/*
	@PostMapping("/login")
	public String postLoginForm (@ModelAttribute MemberDto memberDto, HttpSession httpSession) {
		LOGGER.info("check whether Member or not, NAME : {}", memberDto.getName());
		if (this.checkValidity.isMember(memberDto)) {
			LOGGER.info ("login accepted, NAME : {}", memberDto.getName());
			return "memberList";

		} else {
			LOGGER.info("login denied, NAME : {}", memberDto.getName());
			return "login";
		}
	}
	*/
		
}
