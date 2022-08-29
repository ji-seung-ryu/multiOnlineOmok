package com.example.moo.service.omok;

import java.util.Optional;

public interface OmokRoomRepositoryInterface {
	public void save(OmokRoom omokRoom);
	public Optional<OmokRoom> findByRoomId (String roomId);
}
