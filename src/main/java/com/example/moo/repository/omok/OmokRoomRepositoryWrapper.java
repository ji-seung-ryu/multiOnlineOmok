package com.example.moo.repository.omok;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.moo.service.omok.OmokRoom;
import com.example.moo.service.omok.OmokRoomRepositoryInterface;

@Repository
public class OmokRoomRepositoryWrapper implements OmokRoomRepositoryInterface {

	private OmokRoomRepository omokRoomRepository;
	private final int BOARD_SIZE = 19;
	
	public OmokRoomRepositoryWrapper (OmokRoomRepository omokRoomRepository) {
		this.omokRoomRepository = omokRoomRepository;
	}
	
	@Override
	public void save(OmokRoom omokRoom) {
		OmokRoomEntity entity = toEntity(omokRoom);
		this.omokRoomRepository.save(entity);
	}
	
	@Override
	public Optional<OmokRoom> findByRoomId(String roomId) {
		Optional<OmokRoomEntity> optionalEntity = this.omokRoomRepository.findByRoomId(roomId);
		if (optionalEntity.isPresent()) {
			OmokRoomEntity entity = optionalEntity.get();
			OmokRoom omokRoom = toCore(entity);
			return Optional.of(omokRoom);
		} else {
			return Optional.empty();
		}
	}
	
	private OmokRoomEntity toEntity(OmokRoom omokRoom) {
		OmokRoomEntity entity = new OmokRoomEntity();
		entity.setRoomId(omokRoom.getRoomId());
		entity.setTurn(omokRoom.getTurn());
		entity.setBoard(to1d(omokRoom.getBoard()));
		
		return entity;
	}
	
	private OmokRoom toCore(OmokRoomEntity omokRoomEntity) {
		OmokRoom omokRoom = new OmokRoom();
		omokRoom.setRoomId(omokRoomEntity.getRoomId());
		omokRoom.setTurn(omokRoomEntity.getTurn());
		omokRoom.setBoard(to2d(omokRoomEntity.getBoard()));
		
		return omokRoom;
	}
	
	private int[] to1d(int[][] board2d) {
		int[] board1d = new int [BOARD_SIZE* BOARD_SIZE];
		for (int i=1;i<BOARD_SIZE;i++) {
			for (int j=1;j<BOARD_SIZE;j++) {
				board1d[i*BOARD_SIZE + j] = board2d[i][j];
			}
		}
		return board1d;
	}
	
	private int[][] to2d (int[] board1d) {
		int[][] board2d = new int[BOARD_SIZE][BOARD_SIZE];
		for (int i=1;i<BOARD_SIZE;i++) {
			for (int j=1;j<BOARD_SIZE;j++) {
				board2d[i][j] = board1d[i*BOARD_SIZE + j];
			}
		}
		return board2d;
	}
}