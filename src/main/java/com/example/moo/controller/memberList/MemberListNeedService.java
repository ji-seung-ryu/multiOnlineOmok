package com.example.moo.controller.memberList;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.moo.service.member.Member;
import com.example.moo.service.member.MemberService;

@Component
public class MemberListNeedService {
	private final MemberService memberService;

	public MemberListNeedService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	public List<Member> findAllMember() {
		return this.memberService.findActiveMember();
	}
}
