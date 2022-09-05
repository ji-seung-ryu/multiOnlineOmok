package com.example.moo.controller.webSocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class OmokWebSocketController {
	private final SimpMessagingTemplate simpMessagingTemplate;	

	public OmokWebSocketController (SimpMessagingTemplate template) {
		this.simpMessagingTemplate = template;
	}
	
	@MessageMapping("/message.put")
	public void toUser(@Payload OmokWebSocketMessage message) {
		simpMessagingTemplate.convertAndSend("/queue/" + message.getReceiver(), message);
	}
}
