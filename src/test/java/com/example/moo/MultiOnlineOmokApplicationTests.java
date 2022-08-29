package com.example.moo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.moo.repository.*;
import com.example.moo.repository.member.MemberRepository;
import com.example.moo.repository.member.MemberRepositoryWrapper;
@SpringBootTest
class MultiOnlineOmokApplicationTests {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	MemberRepositoryWrapper memberRepositoryWrapper;
	
	@Test
	void contextLoads() {
	}

}
