package com.example.moo.controller.signup;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.example.moo.common.MemberStatusType;
import com.example.moo.controller.NotFoundException;
import com.example.moo.service.Member;
import com.example.moo.service.MemberNotFoundException;
import com.example.moo.service.MemberService;

@Component
public class SignupNeedService {
	
	private final MemberService memberService;
	
	public SignupNeedService (MemberService memberService) {
		this.memberService = memberService;
	}
	
	public void throwWhenAlreadyExist (String name) {
		try {
			Member member = this.memberService.findMemberByName(name);
			throw new AlreadyExistException();
			
		} catch (MemberNotFoundException e) {
			
		}
	}
	
	public void register (String name, String encodedPassword) {
		Member member = new Member();
		member.setName(name);
		member.setEncodedPassword(encodedPassword);
		member.setState(MemberStatusType.ACTIVE);
		member.setCreateDate(LocalDateTime.now());
		
		this.memberService.saveMember(member);
		
	}
}
