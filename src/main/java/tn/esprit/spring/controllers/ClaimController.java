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
import tn.esprit.spring.service.ClaimService;



@RestController
@RequestMapping("/Claim")
public class ClaimController {
	@Autowired 
	ClaimService cs;

	
	
	
	
	
	@GetMapping("/get-all-Claim")
	@ResponseBody
	public List<Claim> getAllClaim()
	{
		List<Claim> listClaim = cs.retrieveAllClaim();	
		return listClaim; 	
	}
	
	
	
	
	
	
	@PostMapping("/addClaim/{id}")
	@ResponseBody
	public Claim addClaim(@RequestBody Claim c,@PathVariable("id") Long id)
	{
		return cs.addClaim(c,id);
	}
	
	
	
	
	
	
	
	
	@PutMapping("/modify-Claim")
	@ResponseBody
	public Claim modifyClient(@RequestBody Claim c)
	{
		return cs.updateClaim(c);
	}
	
	@DeleteMapping("/remove-Claim/{idClaim}")
	@ResponseBody
	public void removeClaim(@PathVariable("idClaim") Long idClaim) {
		cs.deleteClaim(idClaim);
	}
  	
	
  	

	
	@PutMapping("/modify-Claim-byID/{id}")
	@ResponseBody
	public Claim ModifyClaim(@PathVariable("id") Long idClaim,@RequestBody Claim t) {
	return cs.updateClaimById(t, idClaim);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
