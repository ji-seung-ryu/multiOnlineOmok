package com.example.moo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.moo.service.member.Member;
import com.example.moo.service.member.MemberNotFoundException;
import com.example.moo.service.member.MemberRepositoryInterface;
import com.example.moo.service.member.MemberService;

public class MemberServiceTest {
	private final String NAME = "jiseung";
	private final String ENCODEDPASSWORD = "123456789123456789";
	
	MemberRepositoryInterface memberRepositoryInterface = mock(MemberRepositoryInterface.class);
	MemberService memberService = new MemberService (memberRepositoryInterface);
	
	@Test
	public void returnsMemberByName () {
		Member expectedMember = thereIsAMember();
		
		givenAMemberIsFound(expectedMember);
		
		Member actualMember = memberService.findMemberByName(NAME);
		
		assertThat(actualMember).isEqualTo(expectedMember);
	}
	
	@Test
	public void errorWhenMemberIsNotFound () {
		givenAMemberIsNotFound();
		
		assertThatExceptionOfType(MemberNotFoundException.class).isThrownBy(() -> memberService.findMemberByName(NAME));
	}
	
	private Member thereIsAMember() {
		Member member = new Member();
		member.setName(NAME);
		member.setEncodedPassword(ENCODEDPASSWORD);
		member.setCreateDate(LocalDateTime.now());
		
		return member;
	}
	
	private void givenAMemberIsFound (Member expectedMember) {
		Optional<Member> optionalExpectedMember = convertMemberToOptionalMember (expectedMember);
		
		when(memberRepositoryInterface.findByName(NAME)).thenReturn(optionalExpectedMember);
	}
	
	private void givenAMemberIsNotFound () {
		when(memberRepositoryInterface.findByName(anyString())).thenReturn (Optional.empty());
	}
	
	private Optional<Member> convertMemberToOptionalMember(Member member){
		return Optional.ofNullable(member);
	}
	
}
