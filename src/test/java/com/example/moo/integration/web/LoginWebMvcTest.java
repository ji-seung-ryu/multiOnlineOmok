package com.example.moo.integration.web;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.moo.common.MemberStatusType;
import com.example.moo.controller.CheckValidity;
import com.example.moo.controller.LoginController;
import com.example.moo.controller.MemberDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest (LoginController.class)
public class LoginWebMvcTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean 
	private CheckValidity checkValidity;
	
	private static final String NAME = "jiseung";
	private static final String ENCODEDPASSWORD = "asdasd";
	
	/*
	@Test
	public void returnLoginPage() throws Exception {
		this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("LoginPage")));
	}*/
	
	@Test
	public void returnTheMemberExist () throws Exception {
		MemberDto memberDto = givenAMemberExist ();
		this.mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
	            .content(buildUrlEncodedFormEntity(
	         "name", NAME, 
	         "EncodedPassword", ENCODEDPASSWORD
	    )))
		.andExpect(content().string(containsString("accept")));
	}
	
	
	private MemberDto givenAMemberExist () {
		MemberDto memberDto = new MemberDto();
		memberDto.setName(NAME);
		memberDto.setEncodedPassword(ENCODEDPASSWORD);
		memberDto.setState(MemberStatusType.ACTIVE);
		//memberDto.setCreateDate(LocalDateTime.now());
		
		
		when(checkValidity.isMember(any())).thenReturn(true);
		
		return memberDto;
	}
	
	private String buildUrlEncodedFormEntity(String... params) {
	    if( (params.length % 2) > 0 ) {
	       throw new IllegalArgumentException("Need to give an even number of parameters");
	    }
	    StringBuilder result = new StringBuilder();
	    for (int i=0; i<params.length; i+=2) {
	        if( i > 0 ) {
	            result.append('&');
	        }
	        try {
	            result.
	            append(URLEncoder.encode(params[i], StandardCharsets.UTF_8.name())).
	            append('=').
	            append(URLEncoder.encode(params[i+1], StandardCharsets.UTF_8.name()));
	        }
	        catch (UnsupportedEncodingException e) {
	            throw new RuntimeException(e);
	        }
	    }
	    return result.toString();
	 }
}
