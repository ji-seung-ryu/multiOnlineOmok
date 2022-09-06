package com.example.moo.controller.signup;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;

public class SignupControllerTest {
	private static final String NAME = "jiseung";
	private static final String PASSWORD = "asdasd";
	private static final String FAILRETURN = "signup";
	BindingResult bindingResult = mock(BindingResult.class);
	SignupInteractor signupNeedService = mock(SignupInteractor.class);
	SignupController signupController = new SignupController(signupNeedService);
	
	@Test
	public void SignupSuccess() {
		givenBindingResultHasNoErrors();
		givenANameNotExist();
		SignupForm signupForm = givenASignupFormPosted();
		assertDoesNotThrow (()-> signupController.handleSignupForm(signupForm, bindingResult));
	}
	
	@Test
	public void NameAlreadyExistException() {
		givenBindingResultHasNoErrors();
		givenANameAlreadyExist();
		SignupForm signupForm = givenASignupFormPosted();
		assertEquals(signupController.handleSignupForm(signupForm, bindingResult), FAILRETURN);
	}
		
	private SignupForm givenASignupFormPosted() {
		SignupForm signupForm = new SignupForm();
		signupForm.setName(NAME);
		signupForm.setPassword(PASSWORD);
		return signupForm;
	}
	
	private void givenBindingResultHasNoErrors() {
		when(bindingResult.hasErrors()).thenReturn(false);
	}
	
	private void givenANameNotExist() {
		doNothing().when(signupNeedService).throwWhenAlreadyExist(NAME);
	}
	
	private void givenANameAlreadyExist() {
		doThrow(new AlreadyExistException()).when(signupNeedService).throwWhenAlreadyExist(NAME);
	}
}
