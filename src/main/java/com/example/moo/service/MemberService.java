package com.example.moo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MemberService {
	private final MemberRepositoryInterface memberRepositoryInterface;
	
	public MemberService (MemberRepositoryInterface memberRepositoryInterface) {
		this.memberRepositoryInterface = memberRepositoryInterface;
	}
	
	public void saveMember (Member member) {
		this.memberRepositoryInterface.save(member);
	}
	
	public Member findMemberByName (String name) throws MemberNotFoundException {
		Optional <Member> member = this.memberRepositoryInterface.findByName(name);
		if (member.isEmpty()) {
			throw new MemberNotFoundException();
		}
		
		return member.get();
	}
	
	public List<Member> findAllMember () {
		return this.memberRepositoryInterface.findAll();
	}
}
