package com.example.moo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.moo.common.MemberStatusType;
import com.example.moo.service.Member;
import com.example.moo.service.MemberRepositoryInterface;

@Component
public class MemberRepositoryWrapper implements MemberRepositoryInterface {
	private final static Logger LOGGER = LoggerFactory.getLogger(MemberRepositoryWrapper.class);

	private MemberRepository memberRepository;
	
	public MemberRepositoryWrapper (MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Override
	public void save (Member member) {
		MemberEntity memberEntity = convertMemberToMemberEntity(member);
		this.memberRepository.save(memberEntity);
	}
	
	@Override
	public Optional<Member> findByName (String name) {
		Optional<MemberEntity> optionalMemberEntity = this.memberRepository.findByName (name);
		
		if (optionalMemberEntity.isPresent()) {
			MemberEntity memberEntity = optionalMemberEntity.get();
			Member member = convertMemberEntityToMember (memberEntity);
			return Optional.of(member);
		} else {
			return Optional.empty();
		}
	}
	
	@Override
	public List<Member> findAll() {
		
		List<MemberEntity> memberEntityList = this.memberRepository.findAll();
		List<Member>memberList = convertMemberEntityListToMemberList(memberEntityList);
		return excludeInactive(memberList);
	}

	private List<Member> convertMemberEntityListToMemberList (List<MemberEntity> memberEntityList){
		List<Member> MemberList = new ArrayList<Member>();
		
		for (MemberEntity memberEntity : memberEntityList) {
			Member member = convertMemberEntityToMember(memberEntity);
			MemberList.add(member);
		}
		
		return MemberList;
	}
	
	private Member convertMemberEntityToMember (MemberEntity memberEntity) {
		Member member = new Member();
		member.setId(memberEntity.getId());
		member.setName (memberEntity.getName());
		member.setEncodedPassword(memberEntity.getEncodedPassword());
		member.setState(MemberStatusType.valueOf(memberEntity.getState()));
		member.setCreateDate(memberEntity.getCreateDate());
		
		return member;
	}
	
	private MemberEntity convertMemberToMemberEntity (Member member) {
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setId(member.getId());
		memberEntity.setName(member.getName());
		memberEntity.setEncodedPassword(member.getEncodedPassword());
		memberEntity.setState(member.getState().toString());
		memberEntity.setCreateDate(member.getCreateDate());
		
		return memberEntity;
	}

	private List<Member> excludeInactive (List<Member> memberList) {
		List<Member> excludedMemberList = new ArrayList<Member>();
		for (Member member : memberList) {
			LOGGER.info("In memberList, NAME : {}, STATE: {}", member.getName(), member.getState());
			if (member.getState() == MemberStatusType.ACTIVE) {
				LOGGER.info("Active Member,0 NAME : {}", member.getName());
				excludedMemberList.add(member);
			}
		}
		return excludedMemberList;
	}
	
}
