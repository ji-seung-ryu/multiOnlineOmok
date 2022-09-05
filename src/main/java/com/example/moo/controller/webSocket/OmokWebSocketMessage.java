package com.example.moo.controller.webSocket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OmokWebSocketMessage {
	private String content;
	private String sender;
	private String receiver; 
	private MessageType type;
	
	public enum MessageType {
		PUT_STONE
	}
}
