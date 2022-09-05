package com.example.moo.controller.omok;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.moo.controller.login.SessionConstants;
import com.example.moo.controller.memberList.NoSessionException;
import com.example.moo.service.member.Member;
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
	
	@PostMapping("/create")
	@ResponseBody
	public OmokRoom createRoom(@RequestParam String creator, @RequestParam String opposite) {
		OmokRoom room = this.omokRoomService.createOmokRoom(creator, opposite);
		LOGGER.info("Create room, ID : {}", room.getRoomId());
		return room;
	}
	
	@GetMapping("/enter/{roomId}")
	public String enterOmokRoom(@PathVariable(value="roomId") String roomId, HttpServletRequest request, Model model) {
		try {
			LOGGER.info("Find Room, ID : {}", roomId);
			getName(request, model);
			getOmokRoom(roomId, model);
			LOGGER.info("Return OmokRoom, ID : {}", roomId);
			return "omokRoom";
		} catch (NoSessionException e) {
			LOGGER.info("no session, redirect to login page");	
			return "redirect:/login";
		} catch (OmokRoomNotFoundException e) {
			LOGGER.info("No Room, ID : {}", roomId);
			return "error";
		} catch (FullRoomException e) {
			LOGGER.info("Full Room, ID : {}", roomId);
			return "fullRoom";
		}
	}
	
	private String getName (HttpServletRequest request, Model model)  throws NoSessionException{
		String name = findNameBySession(request);
		LOGGER.info("Hello {}!", name);
		model.addAttribute ("name", name);

		return name;
	}
	
	private String findNameBySession (HttpServletRequest request)  throws NoSessionException{
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute(SessionConstants.LOGIN_MEMBER);
		if (name == null) throw new NoSessionException();
		return name;
	}
	
	private OmokRoom getOmokRoom (String roomId, Model model) {
		OmokRoom foundRoom = this.omokRoomService.findByRoomId(roomId);
		model.addAttribute("omokRoom", foundRoom);
		return foundRoom;
	}
}
