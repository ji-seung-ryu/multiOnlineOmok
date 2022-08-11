package com.example.moo.service;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryInterface {
	public void save (Member member);
	public Optional<Member> findByName (String name);
	public List<Member> findAll();
}
