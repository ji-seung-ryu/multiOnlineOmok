package com.example.moo.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import com.example.moo.service.Member;
import com.example.moo.service.MemberService;
import com.example.moo.service.MemberNotFoundException;
//import com.example.moo.config.PasswordEncoderWrapper;

@Component
public class CheckValidity {
	
    private final static Logger LOGGER = LoggerFactory.getLogger(CheckValidity.class);

	private final MemberService memberService;
//	private final PasswordEncoderWrapper passwordEncoderWrapper;
	
	public CheckValidity (MemberService memberService/*, PasswordEncoderWrapper passwordEncoderWrapper*/) {
		this.memberService = memberService;
		//this.passwordEncoderWrapper = passwordEncoderWrapper;
	}
	
	public MemberDto findMemberByNameAndPassword (String name, String password) {
		// if pass
		
		Member foundMember = this.memberService.findMemberByName(name);
		return toDto (foundMember);
		
	}
	
	public boolean isMember (MemberDto memberDto) {
		/*
		Member member = convertMemberDtoToMember(memberDto);
		String name = member.getName();
		String inputEncodedPassword = member.getEncodedPassword();
		String actualEncodedPassword = getEncodedPasswordByName (name);
		*/
		return true;
		
		//return this.passwordEncoderWrapper.matches(inputEncodedPassword, actualEncodedPassword);
	}
	
	public String getEncodedPasswordByName (String name) {
		LOGGER.info("retrieving the EncodedPassword, NAME : {}", name);
		try {
			Member foundMember = this.memberService.findMemberByName (name);
			return foundMember.getEncodedPassword();
		} catch (MemberNotFoundException e) {
			LOGGER.info("No Member, Name : {}", name);
			
			throw new NotFoundException();
		}
	}
	
	
	private Member convertMemberDtoToMember (MemberDto memberDto) {
		Member member = new Member();
		member.setName(memberDto.getName());
		member.setEncodedPassword(memberDto.getEncodedPassword());
		
		return member;
	}
	
	private MemberDto toDto (Member member) {
		MemberDto memberDto = new MemberDto ();
		memberDto.setName(member.getName());
		memberDto.setEncodedPassword(member.getEncodedPassword());
		memberDto.setState(member.getState());
		memberDto.setCreateDate(member.getCreateDate());
		
		return memberDto;
	}
}
