package com.example.moo.controller.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.moo.common.MemberStatusType;
import com.example.moo.controller.login.SessionConstants;
import com.example.moo.controller.memberList.MemberListController;
import com.example.moo.service.member.Member;
import com.example.moo.service.member.MemberService;

@Controller
public class LogoutController {
	private final static Logger LOGGER = LoggerFactory.getLogger(LogoutController.class);

	private final MemberService memberService;
	
	public LogoutController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/logout")
	public String doLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		inactivateMember(session);		
		invalidateSession(session);
		
		return "redirect:/login";
	}
	
	private void inactivateMember(HttpSession session) {
		String name = getNameBySession(session);
		Member member = this.memberService.findMemberByName(name);
		member.setState(MemberStatusType.INACTIVE);
		this.memberService.saveMember(member);
		LOGGER.info("inactivate member, NAME : {}", member.getName());
	}
	
	private String getNameBySession(HttpSession session) {
		return (String)session.getAttribute(SessionConstants.LOGIN_MEMBER);
	}
	private void invalidateSession(HttpSession session) {
		session.invalidate();
	}
}
