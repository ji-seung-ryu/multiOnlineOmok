package com.example.moo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.moo.service.Member;

public class MemberRepositoryWrapper {
	
	private MemberRepository memberRepository;
	
	public MemberRepositoryWrapper (MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public void save (Member member) {
		MemberEntity memberEntity = convertMemberToMemberEntity(member);
		this.memberRepository.save(memberEntity);
	}
	
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
	
	public List<Member> findAll() {
		List<MemberEntity> memberEntityList = this.memberRepository.findAll();
		return convertMemberEntityListToMemberList(memberEntityList);
	}

	private List<Member> convertMemberEntityListToMemberList (List<MemberEntity> memberEntityList){
		List<Member> MemberList = new ArrayList();
		
		for (MemberEntity memberEntity : memberEntityList) {
			Member member = convertMemberEntityToMember(memberEntity);
			MemberList.add(member);
		}
		
		return MemberList;
	}
	private Member convertMemberEntityToMember (MemberEntity memberEntity) {
		Member member = new Member();
		member.setName (memberEntity.getName());
		member.setEncodedPassword(memberEntity.getEncodedPassword());
		member.setCreateDate(memberEntity.getCreateDate());
		
		return member;
	}
	
	private MemberEntity convertMemberToMemberEntity (Member member) {
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setName(member.getName());
		memberEntity.setEncodedPassword(member.getEncodedPassword());
		memberEntity.setCreateDate(member.getCreateDate());
		
		return memberEntity;
	}

	
}
