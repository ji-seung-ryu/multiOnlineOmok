package com.example.moo.controller.signup;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.moo.controller.NotFoundException;

@Controller
public class SignupController {
	
    private final static Logger LOGGER = LoggerFactory.getLogger(SignupController.class);
    private final SignupNeedService signupNeedService;
	
	public SignupController (SignupNeedService signupNeedService) {
		this.signupNeedService = signupNeedService; 
	}
	
	@GetMapping("/")
    public String redirectToSignup () {
    	return "redirect:/signup";
    }
	
	@GetMapping("/signup")
	public String returnSignupPage (SignupForm signupForm) {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String checkLogin (@Valid SignupForm signupForm, BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			LOGGER.info("form has error!");
	        return "signup";
	    }
	
		try {
			LOGGER.info("check whether duplicated or not, NAME : {}", signupForm.getName());
			this.signupNeedService.throwWhenAlreadyExist(signupForm.getName());
			LOGGER.info("Not duplicated one, NAME : {}", signupForm.getName());
			this.signupNeedService.register(signupForm.getName(), signupForm.getPassword());
			return "redirect:/login";			
		} catch (AlreadyExistException e) {
			LOGGER.info("duplicated one, NAME : {}", signupForm.getName());
			return "signup";

		} 
		
	}
	
}
