package com.example.moo.controller.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.moo.common.MemberStatusType;
import com.example.moo.controller.login.SessionConstants;
import com.example.moo.service.Member;
import com.example.moo.service.MemberService;

@Controller
public class LogoutController {
	
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
		Member member = getMemberBySession(session);
		member.setState(MemberStatusType.INACTIVE);
		this.memberService.saveMember(member);
	}
	
	private Member getMemberBySession(HttpSession session) {
		return (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);
	}
	private void invalidateSession(HttpSession session) {
		session.invalidate();

	}
}
