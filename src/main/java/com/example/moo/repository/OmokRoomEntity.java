package com.example.moo.repository;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OmokRoomEntity {
	private final int BOARD_SIZE = 400;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String roomId;
	
    @Column
	int turn;
	
    @Column
	ArrayList<Integer> board = new ArrayList<Integer>(BOARD_SIZE);
	
	
}
