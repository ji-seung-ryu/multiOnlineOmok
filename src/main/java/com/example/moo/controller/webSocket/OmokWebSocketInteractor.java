package com.example.moo.controller.webSocket;

import org.springframework.stereotype.Component;

import com.example.moo.service.omok.OmokRoom;
import com.example.moo.service.omok.OmokRoomService;

@Component
public class OmokWebSocketInteractor {

	private final OmokRoomService omokRoomService;
	
	public OmokWebSocketInteractor(OmokRoomService omokRoomService) {
		this.omokRoomService = omokRoomService;
	}
	
	public void putStone (String roomId, int r, int c, int turn) {
		try {
			OmokRoom room = this.omokRoomService.findByRoomId(roomId);
			int [][] board = room.getBoard();
			board[r][c] = turn; 
			room.setBoard(board);
			this.omokRoomService.save(room);
			
		} catch(Exception e) {
			
		}
	}
}
