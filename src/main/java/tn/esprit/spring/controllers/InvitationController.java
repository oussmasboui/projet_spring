package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.service.InvitationService;


@RestController
@RequestMapping("/Invitation")
public class InvitationController {
	@Autowired 
	InvitationService cs;

	
	
	
	
	
	@PostMapping("/addInvitation")
	@ResponseBody
	public Invitation addInvitation(@RequestBody Invitation c)
	{
		return cs.addInvitation(c);
	}
	
	
	
	
}
