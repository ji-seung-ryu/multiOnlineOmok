package com.example.moo.controller.memberList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberListController {
	@GetMapping ("/memberList")
	public String returnMemberList() {
		
		return "memberList";
	}
	
}
