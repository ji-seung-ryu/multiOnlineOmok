package com.example.moo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.moo.common.MemberStatusType;
import com.example.moo.service.Member;


public class MemberRepositoryWrapperTest {
	
	private final String NAME = "JISEUNG";
	private final String WRONG_NAME = "jisung";
	private final String PASSWORD = "1234";
	private final String STATE = "ACTIVE";
	
	
	private MemberRepository memberRepository = mock (MemberRepository.class);
	private MemberRepositoryWrapper memberRepositoryWrapper = new MemberRepositoryWrapper(memberRepository);
	
	@Test 
	void returnEmptyListWhenThereAreNoMembers() throws Exception {
		givenThereAreNoMembers();
		
		List<Member> activeMemberList = memberRepositoryWrapper.findActive();
		
		assert(activeMemberList.isEmpty());
	}
	
	@Test
	void returnAMemberListWhenThereIsMember() {
		MemberEntity entity = makeMemberEntity();
		givenThereIsAMember(entity);
		
		List<Member> activeMemberList = memberRepositoryWrapper.findActive();
		Member foundMember = activeMemberList.get(0);
		
		assertThat(activeMemberList).isNotEmpty();
		assertThat(foundMember.getName()).isEqualTo(NAME);
		assertThat(foundMember.getEncodedPassword()).isEqualTo(PASSWORD);
		assertThat(foundMember.getState()).isEqualTo(MemberStatusType.ACTIVE);

	}
	
	@Test
	void returnEmptyWhenFindingWithWrongName() {
		MemberEntity entity = makeMemberEntity();
		givenThereIsAMember(entity);
		
		Optional<Member> maybeEmpty = memberRepositoryWrapper.findByName(WRONG_NAME);
		
		assert(maybeEmpty.isEmpty());
		
	}
	
	@Test
	void returnMemberWhenFindingWithCorrectName() {
		MemberEntity entity = makeMemberEntity();
		givenThereIsAMember(entity);
		
		Optional<Member> foundMember = memberRepositoryWrapper.findByName(NAME);
		
		assert(foundMember.isPresent());
		assertThat(foundMember.get().getName()).isEqualTo(NAME);
		assertThat(foundMember.get().getEncodedPassword()).isEqualTo(PASSWORD);
		assertThat(foundMember.get().getState()).isEqualTo(MemberStatusType.ACTIVE);

	}
	
	
	
	private void givenThereAreNoMembers() {
		when(memberRepository.findAll()).thenReturn(new ArrayList<>());
	}
	
	private void givenThereIsAMember(MemberEntity entity) {
		List<MemberEntity> memberEntityList = new ArrayList<MemberEntity>();
		memberEntityList.add(entity);
		
		when(memberRepository.findAll()).thenReturn(memberEntityList);
		when(memberRepository.findByName(NAME)).thenReturn(Optional.of(entity));
		
	}
	
	private MemberEntity makeMemberEntity() {
		MemberEntity m = new MemberEntity();
		m.setName(NAME);
		m.setEncodedPassword(PASSWORD);
		m.setState(STATE);
		m.setCreateDate(LocalDateTime.now());
		
		return m;
	}
}
