package com.example.moo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class MemberService {
	private final static Logger LOGGER = LoggerFactory.getLogger(MemberService.class);

	private final MemberRepositoryInterface memberRepositoryInterface;
	
	public MemberService (MemberRepositoryInterface memberRepositoryInterface) {
		this.memberRepositoryInterface = memberRepositoryInterface;
	}
	
	public void saveMember (Member member) {
		LOGGER.info("Save Member, NAME: {}", member.getName());
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
		LOGGER.info("Find All Member");
		return this.memberRepositoryInterface.findAll();
	}
	
}
