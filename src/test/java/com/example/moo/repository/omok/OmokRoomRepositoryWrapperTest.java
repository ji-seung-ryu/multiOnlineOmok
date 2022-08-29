package com.example.moo.repository.omok;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.moo.service.omok.OmokRoom;

@SpringBootTest
public class OmokRoomRepositoryWrapperTest {
	
	@Autowired
	private OmokRoomRepositoryWrapper omokRoomRepositoryWrapper;
	
	private final String ROOMID = "1231242342";
	private final int TURN = 1; 
	private final int BOARD_SIZE = 19;
	private final int NONE = 2; 
	private OmokRoom omokRoom;

	
	@Test
	public void findTest() {
		omokRoom = getOmokRoom();
		this.omokRoomRepositoryWrapper.save(omokRoom);
	//	OmokRoom foundRoom = this.omokRoomRepositoryWrapper.findByRoomId(ROOMID).get();
	//	assert(foundRoom).equals(omokRoom);
	}
	
	private OmokRoom getOmokRoom() {
		OmokRoom room = new OmokRoom();
		room.setTurn(TURN);
		room.setRoomId(ROOMID);
		room.setBoard(initBoard());
		
		return room;
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
