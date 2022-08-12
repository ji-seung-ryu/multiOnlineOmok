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

	private final CheckValidity checkValidity;
	
	public LoginController (CheckValidity checkValidity) {
		this.checkValidity = checkValidity; 
	}
	
    
	@GetMapping("/login")
	public String returnLoginPage () {
		return "login";
	}
	
	
	@PostMapping("/login")
	public @ResponseBody String postLoginForm (@ModelAttribute MemberDto memberDto, HttpSession httpSession) {
		LOGGER.info("check whether Member or not, NAME : {}", memberDto.getName());
		
		// 오늘 이것만 하자 form에서 post 보냈을 떄 memberDto 어떻게 오는지, 처리해야 하는지 
		if (this.checkValidity.isMember(memberDto)) {
			LOGGER.info ("login accepted, NAME : {}", memberDto.getName());
			return "accept";

		} else {
			LOGGER.info("login denied, NAME : {}", memberDto.getName());
			return "deny";
		}
	}
	
		
}
