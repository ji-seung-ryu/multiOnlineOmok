package com.example.moo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.moo.service.Member;
import com.example.moo.service.MemberService;

@Controller
public class LoginController {
	private final MemberService memberService;
	
	public LoginController (MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/login")
	public String returnLoginPage () {
		return "LoginPage";
	}
	
	@PostMapping("/login")
	public String checkLoginValidity (@ModelAttribute MemberDto memberDto, HttpSession httpSession) {
		
		return "memberList";
	}
	
	private Member convertToMemberDtoToMember (MemberDto memberDto) {
		Member member = new Member();
		member.setName(memberDto.getName());
		member.setEncodedPassword(memberDto.getEncodedPassword());
		
		return member;
	}
}
