package com.example.moo.controller.signup;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.moo.common.MemberStatusType;
import com.example.moo.config.PasswordEncoder;
import com.example.moo.controller.NotFoundException;
import com.example.moo.service.Member;
import com.example.moo.service.MemberNotFoundException;
import com.example.moo.service.MemberService;

@Component
public class SignupNeedService {
	
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	public SignupNeedService (MemberService memberService, PasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}
	
	public void throwWhenAlreadyExist (String name) {
		try {
			Member member = this.memberService.findMemberByName(name);
			throw new AlreadyExistException();
			
		} catch (MemberNotFoundException e) {
			
		}
	}
	
	public void register (String name, String password) {
		Member member = new Member();
		String encodedPassword = this.passwordEncoder.encode(password);
		member.setName(name);
		member.setEncodedPassword(encodedPassword);
		member.setState(MemberStatusType.ACTIVE);
		member.setCreateDate(LocalDateTime.now());
		
		this.memberService.saveMember(member);
		
	}
}
