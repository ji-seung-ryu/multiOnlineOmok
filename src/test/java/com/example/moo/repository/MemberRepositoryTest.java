package com.example.moo.repository;

import java.time.LocalDateTime;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;
	
	private final String NAME = "JISEUNG";
	private final String ENCODEDPASSWORD = "123456789123456789";
	
	@Test
	void saveMember() {
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setName(NAME);
		memberEntity.setEncodedPassword(ENCODEDPASSWORD);
		memberEntity.setCreateDate(LocalDateTime.now());
		
		memberRepository.save(memberEntity);
	}
}
