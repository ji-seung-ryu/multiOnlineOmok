package com.example.moo.repository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.example.moo.service.Member;


public class MemberRepositoryWrapperTest {
	
	private MemberRepository memberRepository = mock (MemberRepository.class);
	private MemberRepositoryWrapper memberRepositoryWrapper = new MemberRepositoryWrapper(memberRepository);
	
	@Test 
	void returnEmptyListWhenThereAreNoMembers() throws Exception {
		givenThereAreNoMembers();
		
		List<Member> memberList = memberRepositoryWrapper.findAll();
		
		assert(memberList.isEmpty());
	}
	
	private void givenThereAreNoMembers() {
		when(memberRepository.findAll()).thenReturn(new ArrayList<>());
	}
	
}
