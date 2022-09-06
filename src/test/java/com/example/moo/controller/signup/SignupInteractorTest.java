package com.example.moo.controller.signup;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.example.moo.config.encoder.PasswordEncoder;
import com.example.moo.service.member.Member;
import com.example.moo.service.member.MemberNotFoundException;
import com.example.moo.service.member.MemberService;

public class SignupInteractorTest {
	private final String NAME = "jiseung";
	
	private final MemberService memberService = mock(MemberService.class);
	private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
	private final SignupInteractor signupNeedService = new SignupInteractor(memberService, passwordEncoder);
	

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
