package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Claim;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.service.InvitationService;


@RestController
@RequestMapping("/Invitation")
public class InvitationController {
	@Autowired 
	InvitationService cs;

	
	
	
	
	
	@PostMapping("/addInvitation/{id}")
	@ResponseBody
	public Invitation addInvitation(@RequestBody Invitation c,@PathVariable("id") Long id)
	{
		return cs.addInvitation(c,id);
	}
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/get-all-Invitation")
	@ResponseBody
	public List<Invitation> getAllInvitation()
	{
		List<Invitation> listInvitation = cs.retrieveAllInvitation();	
		return listInvitation; 	
	}
	
	
	@DeleteMapping("/remove-Invitation/{idInvitation}")
	@ResponseBody
	public void removeInvitation(@PathVariable("idInvitation") Long idInvitation) {
		cs.deleteInvitation(idInvitation);
	}
  	
  	
	@PutMapping("/modify-Invitation")
	@ResponseBody
	public Invitation modifyInvitation(@RequestBody Invitation c)
	{
		return cs.updateInvitation(c);
	}
	
	
	
	
	
	@PutMapping("/modify-Invitation-byID/{id}")
	@ResponseBody
	public Invitation ModifyInvitation(@PathVariable("id") Long idInvitation,@RequestBody Invitation t) {
	return cs.updateInvitationById(t, idInvitation);
	}
	
	
	
	
	
}
