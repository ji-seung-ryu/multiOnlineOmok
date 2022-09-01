package com.example.moo.controller.memberList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.moo.controller.login.LoginController;
import com.example.moo.controller.login.SessionConstants;
import com.example.moo.service.member.Member;

@Controller
public class MemberListController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(MemberListController.class);
	private final MemberListNeedService memberListNeedService;
	
	public MemberListController(MemberListNeedService memberListNeedService) {
		this.memberListNeedService = memberListNeedService;
	}
	/*
	@GetMapping ("/memberList")
	public String returnMemberList(@RequestParam("name") String name, Model model) {
		model.addAttribute("name" , name); 
		return "memberList";
	}
	*/
	
	@GetMapping ("/memberList")
	public String returnMemberList (HttpServletRequest request, Model model) {
		try {
			getLoginMember(request, model);
			getMemberList(model);
			
			return "memberList";
		} catch (NoSessionException e) {
			LOGGER.info("no session, redirect to login page");	
			return "redirect:/login";
		}
	}
	
	private Member getLoginMember (HttpServletRequest request, Model model)  throws NoSessionException{
		Member member = findLoginMemberBySession(request);
		LOGGER.info("Hello {}!", member.getName());
		model.addAttribute ("name", member.getName());

		return member;
	}
	
	private Member findLoginMemberBySession (HttpServletRequest request)  throws NoSessionException{
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);
		if (member == null) throw new NoSessionException();
		return member;
	}
	
	private void getMemberList (Model model) {
		List<Member> memberList = this.memberListNeedService.findAllMember();
		model.addAttribute("memberList", memberList);
	}
}
