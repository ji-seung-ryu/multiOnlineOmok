package com.example.moo.integration.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.moo.repository.MemberRepositoryWrapper;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.moo.service.Member;

@SpringBootTest
public class databaseIntegrationTest {

	@Autowired
	MemberRepositoryWrapper memberRepositoryWrapper;
	
	private final String name = "JISEUNG";
	private final String encodedPassword = "123456789123456789";
	
	@Test
	public void saveMember () {
		Member member = new Member();
		member.setName(name);
		member.setEncodedPassword(encodedPassword);
		member.setCreateDate(LocalDateTime.now());
		
		this.memberRepositoryWrapper.save(member);
	}
	
}
