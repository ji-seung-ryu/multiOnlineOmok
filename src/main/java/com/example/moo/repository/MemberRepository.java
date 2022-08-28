package com.example.moo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
	Optional<MemberEntity> findByName(String name);
	
}
