package com.example.moo.controller.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.moo.config.PasswordEncoder;
import com.example.moo.service.Member;
import com.example.moo.service.MemberNotFoundException;
import com.example.moo.service.MemberService;

@Component
public class LoginNeedService {
	private final static Logger LOGGER = LoggerFactory.getLogger(LoginNeedService.class);

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	public LoginNeedService(MemberService memberService, PasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;

	}

	public Member doLogin(String name, String password) throws NameNotFoundException, WrongPasswordException {
		try {
			Member member = this.memberService.findMemberByName(name);
			if (!this.passwordEncoder.matches(password, member.getEncodedPassword()))
				throw new WrongPasswordException();

			return member;
		} catch (MemberNotFoundException e) {
			throw new NameNotFoundException();
		}
	}
}