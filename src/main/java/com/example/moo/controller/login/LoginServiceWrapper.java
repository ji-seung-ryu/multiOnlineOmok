package com.example.moo.controller.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.moo.config.encoder.PasswordEncoder;
import com.example.moo.service.member.Member;
import com.example.moo.service.member.MemberNotFoundException;
import com.example.moo.service.member.MemberService;

@Component
public class LoginServiceWrapper {
	private final static Logger LOGGER = LoggerFactory.getLogger(LoginServiceWrapper.class);

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	public LoginServiceWrapper(MemberService memberService, PasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;

	}

	public Member doLogin(String name, String password){
		try {
			LOGGER.info("find member by NAME : {}", name);
			Member member = this.memberService.findMemberByName(name);
			if (!this.passwordEncoder.matches(password, member.getEncodedPassword())) {
				LOGGER.info("Wrong password, NAME : {}", name);
				throw new WrongPasswordException();
			}
			LOGGER.info("Login Success, NAME : {}", name);
			return member;
		} catch (MemberNotFoundException e) {
			LOGGER.info("Name is not found, NAME : {}", name);
			throw new NameNotFoundException();
		}
	}
	
	public void saveMember (Member member) {
		this.memberService.saveMember(member);
	}
}
