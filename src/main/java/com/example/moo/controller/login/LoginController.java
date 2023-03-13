package com.example.moo.controller.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.moo.common.MemberStatusType;
import com.example.moo.service.member.Member;

@Controller
public class LoginController {

	private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	private final LoginServiceWrapper loginNeedService;

	public LoginController(LoginServiceWrapper loginNeedService) {
		this.loginNeedService = loginNeedService;
	}

	@GetMapping("/login")
	public String returnLoginPage(LoginForm loginForm) {
		return "login";
	}

	@PostMapping("/login")
	public String handleLoginForm(@Valid LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			LOGGER.info("form has error!");
			return "login";
		}

		try {
			Member loginMember = doLogin(loginForm);
			activateMember(loginMember);
			setSession(request, loginMember.getName());
			
			return "redirect:/memberList";
		} catch (NameNotFoundException e) {
			LOGGER.info("not found, NAME : {}", loginForm.getName());
			return "login";
		} catch (WrongPasswordException e) {
			LOGGER.info("wrong password, NAME : {}", loginForm.getName());
			return "login";
		}
	}

	public Member doLogin(LoginForm loginForm) throws NameNotFoundException, WrongPasswordException {

		Member member = this.loginNeedService.doLogin(loginForm.getName(), loginForm.getPassword());
		return member;

	}

	private void activateMember(Member member) {
		member.setState(MemberStatusType.ACTIVE);
		this.loginNeedService.saveMember(member);
	}
		
	private void setSession(HttpServletRequest request, String name) {
		HttpSession session = request.getSession();
		session.setAttribute(SessionConstants.LOGIN_MEMBER, name);
	}

}
