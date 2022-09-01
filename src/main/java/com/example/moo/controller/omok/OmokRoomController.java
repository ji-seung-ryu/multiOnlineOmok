package com.example.moo.controller.omok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.moo.service.omok.FullRoomException;
import com.example.moo.service.omok.OmokRoom;
import com.example.moo.service.omok.OmokRoomNotFoundException;
import com.example.moo.service.omok.OmokRoomService;

@Controller
public class OmokRoomController {
	private final static Logger LOGGER = LoggerFactory.getLogger(OmokRoomController.class);

	private final OmokRoomService omokRoomService;
	
	public OmokRoomController(OmokRoomService omokRoomService) {
		this.omokRoomService = omokRoomService;
	}
	
	@GetMapping("/create")
	public String createOmokRoom() {
		OmokRoom omokRoom = this.omokRoomService.createOmokRoom();
		LOGGER.info("Create room, ID : {}", omokRoom.getRoomId());
		return "redirect:/enter/" + omokRoom.getRoomId(); 
	}
	
	@PostMapping("/create")
	@ResponseBody
	public OmokRoom createRoom() {
		OmokRoom room = this.omokRoomService.createOmokRoom();
		LOGGER.info("Create room, ID : {}", room.getRoomId());
		return room;
	}
	
	@GetMapping("/enter/{roomId}")
	public String enterOmokRoom(@PathVariable(value="roomId") String roomId, Model model) {
		try {
			LOGGER.info("Find Room, ID : {}", roomId);
			OmokRoom foundRoom = this.omokRoomService.findByRoomId(roomId);
			model.addAttribute(foundRoom);
			LOGGER.info("Return OmokRoom, ID : {}", roomId);
			return "omokRoom";
		} catch (OmokRoomNotFoundException e) {
			LOGGER.info("No Room, ID : {}", roomId);
			return "error";
		} catch (FullRoomException e) {
			LOGGER.info("Full Room, ID : {}", roomId);
			return "fullRoom";
		}
	}
}
