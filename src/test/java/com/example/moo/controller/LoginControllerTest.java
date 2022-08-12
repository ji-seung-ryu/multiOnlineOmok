package com.example.moo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.moo.common.MemberStatusType;

        
public class LoginControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean 
	private CheckValidity checkValidity;
	
	private static final String NAME = "jiseung";
	private static final String ENCODEDPASSWORD = "asdasd";
	
	@Test
	public void returnLoginPage() throws Exception {
		this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("LoginPage")));
	}
	
	@Test
	public void returnTheMemberExist () {
		MemberDto memberDto = givenAMemberExist ();
		
	}
	
	
	private MemberDto givenAMemberExist () {
		MemberDto memberDto = new MemberDto();
		memberDto.setName(NAME);
		memberDto.setEncodedPassword(ENCODEDPASSWORD);
		memberDto.setState(MemberStatusType.ACTIVE);
		memberDto.setCreateDate(LocalDateTime.now());
		
		when(checkValidity.isMember(memberDto)).thenReturn(true);
		
		return memberDto;
	}
}
