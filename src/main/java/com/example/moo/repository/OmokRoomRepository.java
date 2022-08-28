package com.example.moo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OmokRoomRepository extends JpaRepository<OmokRoomEntity, Integer> {
	Optional<OmokRoomEntity> findByRoomId(String roomId);
	
}
