package com.example.moo.controller.login;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.moo.common.MemberStatusType;
import com.example.moo.service.member.Member;

public class LoginControllerTest {
	private static final String NAME = "jiseung";
	private static final String PASSWORD = "asdasd";
	

	private LoginInteractor loginNeedService = mock(LoginInteractor.class);
	private LoginController loginController = new LoginController(loginNeedService);
	
	@Test
	public void loginSuccess() {
		Member member = givenAMemberExist();
		LoginForm loginForm = givenaALoginFormPosted();
		when(loginController.doLogin(loginForm)).thenReturn(member);
	}
	
	@Test
	public void loginNameNotFoundTest() {
		givenAMemberNotExist();
		LoginForm loginForm = givenaALoginFormPosted();
        assertThatExceptionOfType(NameNotFoundException.class).isThrownBy(() -> loginController.doLogin(loginForm));
	}
	
	@Test 
	public void loginWrongPasswordTest() {
		givenAWrongPassword();
		LoginForm loginForm = givenaALoginFormPosted();
        assertThatExceptionOfType(WrongPasswordException.class).isThrownBy(() -> loginController.doLogin(loginForm));	
    }
		
	private Member givenAMemberExist() {
		Member member = new Member();
		member.setName(NAME);
		member.setEncodedPassword(PASSWORD);
		member.setCreateDate(LocalDateTime.now());
		member.setState(MemberStatusType.ACTIVE);
		when(loginNeedService.doLogin(NAME, PASSWORD)).thenReturn(member);
		return member;
	}
	
	private LoginForm givenaALoginFormPosted () {
		LoginForm loginForm = new LoginForm();
		loginForm.setName(NAME);
		loginForm.setPassword(PASSWORD);
		return loginForm;
	}
	
	private void givenAMemberNotExist() {	
		when(loginNeedService.doLogin(NAME, PASSWORD)).thenThrow(new NameNotFoundException());
	}
	
	private void givenAWrongPassword() {	
		when(loginNeedService.doLogin(NAME, PASSWORD)).thenThrow(new WrongPasswordException());
	}	
}
