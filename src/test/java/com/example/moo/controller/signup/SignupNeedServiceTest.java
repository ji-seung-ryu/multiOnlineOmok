package com.example.moo.controller.signup;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.example.moo.config.PasswordEncoder;
import com.example.moo.service.Member;
import com.example.moo.service.MemberNotFoundException;
import com.example.moo.service.MemberService;

public class SignupNeedServiceTest {
	private final String NAME = "jiseung";
	
	private final MemberService memberService = mock(MemberService.class);
	private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
	private final SignupNeedService signupNeedService = new SignupNeedService(memberService, passwordEncoder);
	

	@Test
	void memberNotFound() {
		givenAMemberNotFound();
		assertDoesNotThrow (()-> signupNeedService.throwWhenAlreadyExist(NAME));
	}
	
	@Test
	void memberAleadyExist() {
		givenAMemberFound();
        assertThatExceptionOfType(AlreadyExistException.class).isThrownBy(() -> signupNeedService.throwWhenAlreadyExist(NAME));
	}
	
	private void givenAMemberNotFound() {
		when(memberService.findMemberByName(NAME)).thenThrow(new MemberNotFoundException());
	}
	
	private void givenAMemberFound() {
		Member member = new Member();
		member.setName(NAME);
		
		when(memberService.findMemberByName(NAME)).thenReturn(member);
	}
	
	
}