package com.example.moo.service.omok;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class OmokRoomService {
	
	private final OmokRoomRepositoryInterface omokRoomRepositoryInterface;
	private final int BLACK_TURN = 0;
	private final int BOARD_SIZE = 19; 
	private final int NONE = 2; 
	
	public OmokRoomService (OmokRoomRepositoryInterface omokRoomRepositoryInterface) {
		this.omokRoomRepositoryInterface = omokRoomRepositoryInterface;
	}
	
	public OmokRoom createOmokRoom(String creator, String opposite) {
		OmokRoom omokRoom = new OmokRoom();
		omokRoom.setRoomId(UUID.randomUUID().toString());
		omokRoom.setCreator(creator);
		omokRoom.setOpposite(opposite);
		omokRoom.setTurn(BLACK_TURN);
		omokRoom.setBoard(initBoard());
		save(omokRoom);
		
		return omokRoom;
	}
	
	public void save(OmokRoom omokRoom) {
		this.omokRoomRepositoryInterface.save(omokRoom);
	}
	
	public OmokRoom findByRoomId(String roomId) {
		Optional <OmokRoom> omokRoom = this.omokRoomRepositoryInterface.findByRoomId(roomId);
		if (omokRoom.isEmpty()) {
			throw new OmokRoomNotFoundException();
		}
		
		return omokRoom.get();
	}
	
	private int[][] initBoard(){
		int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
		for (int i=1;i<BOARD_SIZE;i++) {
			for (int j=1;j<BOARD_SIZE;j++) {
				board[i][j] = NONE;
			}
		}
		
		return board; 
	}
	
}
