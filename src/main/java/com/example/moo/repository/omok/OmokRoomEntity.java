package com.example.moo.repository.omok;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class OmokRoomEntity {
	@Id
	private String roomId;
	
    @Column
	private int turn;
	
    @Lob
    private String board;

}
