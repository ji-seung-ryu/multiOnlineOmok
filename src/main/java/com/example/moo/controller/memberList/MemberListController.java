package com.example.moo.controller.memberList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberListController {
	@GetMapping ("/memberList")
	public String returnMemberList(@RequestParam("name") String name, Model model) {
		model.addAttribute("name" , name); 
		return "memberList";
	}
	
}
