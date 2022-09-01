package com.example.moo.controller.webSocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MemberWebSocketController {
	
	private final SimpMessagingTemplate simpMessagingTemplate;	

	public MemberWebSocketController (SimpMessagingTemplate template) {
		this.simpMessagingTemplate = template;
	}
	
	@MessageMapping("/message.register")
	@SendTo("/topic/public")
	public MemberWebSocketMessage register(@Payload MemberWebSocketMessage message, SimpMessageHeaderAccessor headerAccessor) {
	
		headerAccessor.getSessionAttributes().put("username", message.getSender());
		return message;
	}
	
	@MessageMapping("/message.fight")
	public void toUser(@Payload MemberWebSocketMessage message) {
		simpMessagingTemplate.convertAndSend("/queue/" + message.getReceiver(), message);
	}
	
}
