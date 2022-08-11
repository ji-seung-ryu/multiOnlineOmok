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
	
	private final String NAME = "JISEUNG";
	private final String ENCODEDPASSWORD = "123456789123456789";
	
	@Test
	public void saveMember () {
		Member member = new Member();
		member.setName(NAME);
		member.setEncodedPassword(ENCODEDPASSWORD);
		member.setCreateDate(LocalDateTime.now());
		
		this.memberRepositoryWrapper.save(member);
	}
	
}
