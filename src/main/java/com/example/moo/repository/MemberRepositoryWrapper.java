package com.example.moo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.moo.service.Member;
import com.example.moo.service.MemberRepositoryInterface;

@Component
public class MemberRepositoryWrapper implements MemberRepositoryInterface {
	
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
		return convertMemberEntityListToMemberList(memberEntityList);
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
		member.setName (memberEntity.getName());
		member.setEncodedPassword(memberEntity.getEncodedPassword());
		member.setState(memberEntity.getState());
		member.setCreateDate(memberEntity.getCreateDate());
		
		return member;
	}
	
	private MemberEntity convertMemberToMemberEntity (Member member) {
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setName(member.getName());
		memberEntity.setEncodedPassword(member.getEncodedPassword());
		memberEntity.setState(member.getState());
		memberEntity.setCreateDate(member.getCreateDate());
		
		return memberEntity;
	}

	
}
