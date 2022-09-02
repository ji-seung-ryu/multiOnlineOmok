package com.example.moo.service.omok;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OmokRoom {	
	private String roomId;
	private String creator, opposite;
	private int turn;
	private int[][] board;
	
}
