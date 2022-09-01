package com.example.moo.controller.login;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.moo.common.MemberStatusType;
import com.example.moo.config.encoder.PasswordEncoder;
import com.example.moo.service.member.Member;
import com.example.moo.service.member.MemberNotFoundException;
import com.example.moo.service.member.MemberService;

public class LoginNeedServiceTest {
	private final String NAME = "JISEUNG";
	private final String PASSWORD = "12855";
	
	MemberService memberService = mock(MemberService.class);
	PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
	LoginNeedService loginNeedService = new LoginNeedService(memberService, passwordEncoder);
	
	@Test
	void successLogin() {
		givenAMemberExist();
		givenAPasswordMatch();
		assertDoesNotThrow (()-> loginNeedService.doLogin(NAME, PASSWORD));
	}
	
	@Test 
	void wrongPasswordTest() {
		givenAMemberExist();
		givenAPasswordNotMatch();
        assertThatExceptionOfType(WrongPasswordException.class).isThrownBy(() -> loginNeedService.doLogin(NAME, PASSWORD));	
	}
	
	@Test 
	void nameNotFoundTest() {
		givenAMemberNotExist();
        assertThatExceptionOfType(NameNotFoundException.class).isThrownBy(() -> loginNeedService.doLogin(NAME, PASSWORD));	
	}

	private Member givenAMemberExist() {
		Member member = new Member();
		member.setName(NAME);
		member.setEncodedPassword(PASSWORD);
		member.setState(MemberStatusType.ACTIVE);
		member.setCreateDate(LocalDateTime.now());
		
		when (memberService.findMemberByName(NAME)).thenReturn(member);
		
		return member;
	}
	
	private void givenAMemberNotExist() {
		when (memberService.findMemberByName(NAME)).thenThrow(new MemberNotFoundException());

	}
	
	private void givenAPasswordMatch() {
		when(passwordEncoder.matches(any(),any())).thenReturn(true);
	}
	
	private void givenAPasswordNotMatch() {
		when(passwordEncoder.matches(any(),any())).thenReturn(false);
	} 
}
