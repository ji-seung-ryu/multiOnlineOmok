package com.example.moo.service.omok;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface OmokRoomRepositoryInterface {
	public void save(OmokRoom omokRoom);
	public Optional<OmokRoom> findByRoomId (String roomId);
}
