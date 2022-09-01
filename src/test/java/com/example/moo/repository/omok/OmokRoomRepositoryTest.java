package com.example.moo.repository.omok;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OmokRoomRepositoryTest {
	@Autowired
	private OmokRoomRepository omokRoomRepository;
	
	private final String ROOMID = "1233";
	private final int turn = 1;
	private final int BOARD_SIZE = 19;
	private final int NONE = 2; 
	
	@Test
	void saveMember() {
		OmokRoomEntity entity = new OmokRoomEntity();
		entity.setRoomId(ROOMID);
		entity.setTurn(turn);
		System.out.println(to1d(initBoard()));
		entity.setBoard(to1d(initBoard()));
		
		this.omokRoomRepository.save(entity);
		
		Optional<OmokRoomEntity> foundEntity = this.omokRoomRepository.findByRoomId(ROOMID);
		
		assert(foundEntity.get().getBoard()).equals(entity.getBoard());
	}
	
	private String to1d(int[][] board2d) {
		String board1d = new String();
		for (int i=1;i<BOARD_SIZE;i++) {
			for (int j=1;j<BOARD_SIZE;j++) {
				board1d += board2d[i][j];
			}
		}
		return board1d;
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
