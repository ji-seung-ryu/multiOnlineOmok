package com.example.moo.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.moo.service.Member;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberRepositoryWrapperTest {
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private MemberRepositoryWrapper memberRepositoryWrapper;
	private final String name = "JISEUNG";
	private final String encodedPassword = "123456789123456789";
	
	@Transactional
	@Test
	void saveTest () {
		Member member = new Member();
		member.setName(name);
		member.setEncodedPassword(encodedPassword);
		member.setCreateDate(LocalDateTime.now());
		
		this.memberRepositoryWrapper.save(member);
	}
	
}
