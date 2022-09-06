package com.example.moo.controller.webSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.moo.service.omok.OmokRoom;
import com.example.moo.service.omok.OmokRoomNotFoundException;
import com.example.moo.service.omok.OmokRoomService;

@Component
public class OmokWebSocketInteractor {
	private final static Logger LOGGER = LoggerFactory.getLogger(OmokWebSocketInteractor.class);

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
			
		} catch(OmokRoomNotFoundException e) {
			LOGGER.info("No Room, roomId : {}", roomId);
		}
	}
	
}
