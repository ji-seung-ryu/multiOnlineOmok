package com.example.moo.controller.memberList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.moo.controller.login.SessionConstants;
import com.example.moo.service.Member;

@Controller
public class MemberListController {
	
	/*
	@GetMapping ("/memberList")
	public String returnMemberList(@RequestParam("name") String name, Model model) {
		model.addAttribute("name" , name); 
		return "memberList";
	}
	*/
	
	@GetMapping ("/memberList")
	public String returnMemberList (HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);
		
		if (member == null) return "login";
		
		model.addAttribute ("name", member.getName());
		return "memberList";
	}
}
