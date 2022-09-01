package com.example.moo.integration.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.example.moo.common.MemberStatusType;
import com.example.moo.repository.member.MemberRepositoryWrapper;
import com.example.moo.service.member.Member;

@SpringBootTest
public class databaseIntegrationTest {

	@Autowired
	MemberRepositoryWrapper memberRepositoryWrapper;
	
	private final Integer ID = 4;
	private final String NAME = "JssISEUNG";
	private final String ENCODEDPASSWORD = "123456789123456789";
	
	@Test
	public void saveMember () {
		Member member = new Member();
		member.setId(ID);
		member.setName(NAME);
		member.setEncodedPassword(ENCODEDPASSWORD);
		member.setState(MemberStatusType.ACTIVE);
		member.setCreateDate(LocalDateTime.now());
		
		this.memberRepositoryWrapper.save(member);
		
		Member foundMember = this.memberRepositoryWrapper.findByName(NAME).get();
		
		assert(foundMember.getEncodedPassword()).equals(member.getEncodedPassword());
	}
	
}
