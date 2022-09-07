package com.example.moo.controller.omok;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import com.example.moo.controller.login.SessionConstants;
import com.example.moo.service.omok.FullRoomException;
import com.example.moo.service.omok.OmokRoom;
import com.example.moo.service.omok.OmokRoomNotFoundException;
import com.example.moo.service.omok.OmokRoomService;

public class OmokRoomControllerTest {
	private final int BLACK_TURN = 0;
	private final int BOARD_SIZE = 19; 
	private final int NONE = 2; 
	private final String CREATOR = "creator";
	private final String ROOMID = "12341234";
	private final String WRONG_ROOMID = "12341111234";
	private final String FULL_ROOMID = "FULLROOMID";

	private final String CORRECT_URL = "omokRoom";
	private Model model = mock(Model.class);
	private HttpSession session = mock(HttpSession.class);
	private HttpServletRequest request= (HttpServletRequest) mock(HttpServletRequest.class);
	private OmokRoomService omokRoomService = mock(OmokRoomService.class);
	private final OmokRoomController omokRoomController = new OmokRoomController(omokRoomService);
	
	
	@Test
	void enterRoom() {
		givenACreatorSessionExist();
		givenARoomExist();
		String returnUrl = omokRoomController.enterOmokRoom(ROOMID, request, model);
		assertThat(returnUrl).isEqualTo(CORRECT_URL);
	}
	
	@Test
	void enterNotExistingRoom() {
		givenACreatorSessionExist();
		givenARoomDoesNotExist();
		String returnUrl = omokRoomController.enterOmokRoom(WRONG_ROOMID, request, model);
		assertThat(returnUrl).isNotEqualTo(CORRECT_URL);

	}
	
	@Test
	void enterFullRoom() {
		givenACreatorSessionExist();
		givenARoomFull();
		String returnUrl = omokRoomController.enterOmokRoom(FULL_ROOMID, request,  model);
		assertThat(returnUrl).isNotEqualTo(CORRECT_URL);

	}
	
	private void givenACreatorSessionExist() {
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute(SessionConstants.LOGIN_MEMBER)).thenReturn(CREATOR);
	}
	
	
	private void givenARoomExist() {
		OmokRoom omokRoom = new OmokRoom();
		omokRoom.setRoomId(UUID.randomUUID().toString());
		omokRoom.setTurn(BLACK_TURN);
		omokRoom.setBoard(initBoard());
		when(omokRoomService.findByRoomId(ROOMID)).thenReturn(omokRoom);
	}
	
	private void givenARoomDoesNotExist() {
		when(omokRoomService.findByRoomId(WRONG_ROOMID)).thenThrow(new OmokRoomNotFoundException());
	}
	
	private void givenARoomFull() {
		when(omokRoomService.findByRoomId(FULL_ROOMID)).thenThrow(new FullRoomException());
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
