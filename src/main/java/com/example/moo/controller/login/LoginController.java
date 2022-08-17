package com.example.moo.controller.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.moo.config.PasswordEncoder;
import com.example.moo.controller.MemberDto;
import com.example.moo.controller.NotFoundException;
import com.example.moo.service.Member;
import com.example.moo.service.MemberService;

@Controller
public class LoginController {
	
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	private final LoginNeedService loginNeedService;
	
	public LoginController (LoginNeedService loginNeedService) {
		this.loginNeedService = loginNeedService; 
	}
	
	@GetMapping("/login")
	public String returnLoginPage (LoginForm loginForm) {	
		return "login";
	}
	
	
	@PostMapping("/login")
	public String doLogin (@Valid LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			 LOGGER.info("form has error!");
	         return "login";
	    }
		
		
		try {
			Member member = this.loginNeedService.doLogin (loginForm.getName(), loginForm.getPassword());
			redirectAttributes.addAttribute("name", member.getName());
			HttpSession session = request.getSession();
			session.setAttribute(SessionConstants.LOGIN_MEMBER, member);
			
			return "redirect:/memberList";
		} catch (NameNotFoundException e) {
			LOGGER.info("not found, NAME : {}", loginForm.getName());
			return "login";
		} catch (WrongPasswordException e) {
			LOGGER.info("wrong password, NAME : {}", loginForm.getName());
			return "login";	
		}
		
	}
		
}
