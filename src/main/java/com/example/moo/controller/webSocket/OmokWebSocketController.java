package com.example.moo.controller.webSocket;

import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class OmokWebSocketController {
	private final SimpMessagingTemplate simpMessagingTemplate;	
	private final OmokWebSocketInteractor interactor;

	public OmokWebSocketController (SimpMessagingTemplate template, OmokWebSocketInteractor interactor) {
		this.simpMessagingTemplate = template;
		this.interactor = interactor;
	}
	
	@MessageMapping("/message.put")
	public void toUser(@Payload OmokWebSocketMessage message) {
		String content = message.getContent();
		JSONObject obj = new JSONObject(content);
		int r = obj.getInt("r");
		int c = obj.getInt("c");
		int turn = obj.getInt("turn");
		String roomId = obj.getString("roomId");
		
		this.interactor.putStone(roomId, r,c,turn);
		
		simpMessagingTemplate.convertAndSend("/queue/" + message.getReceiver(), message);
	}
}
