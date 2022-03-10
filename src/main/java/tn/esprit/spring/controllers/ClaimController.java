package tn.esprit.spring.controllers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Claim;
import tn.esprit.spring.repository.ClaimRepository;
import tn.esprit.spring.service.ClaimService;

import org.springframework.ui.Model;

@RestController
@RequestMapping("/Claim")
public class ClaimController {
	@Autowired 
	ClaimService cs;

	@Autowired
	ClaimRepository cr;
	
	
	
	
	@GetMapping("/get-all-Claim")
	@ResponseBody
	public List<Claim> getAllClaim()
	{
		List<Claim> listClaim = cs.retrieveAllClaim();	
		return listClaim; 	
	}
	
	@GetMapping("/filterByState/{state}")
	@ResponseBody
	public List<Claim> filterClaims(@PathVariable("state") Boolean state)
	{
		List<Claim> listClaim = cr.filterClaims(state);	
		return listClaim; 	
	}
	
	@GetMapping("/stat/etat")
	@ResponseBody
	public String statEtat()
	{
		return cs.getEtat();
	}
	
	@GetMapping("/trierParDate")
	@ResponseBody
	public List<Claim> trierParDate()
	{
		List<Claim> listClaim = cr.trierParDate();	
		return listClaim; 	
	}
	
	
	
	@PostMapping("/addClaim/{id}")
	@ResponseBody
	public Claim addClaim(@RequestBody Claim c,@PathVariable("id") Long id)
	{
		return cs.addClaim(c,id);
	}
	
	
	
	@PutMapping("/claimCheck/{id}")
	@ResponseBody
	public void claimCheck(@PathVariable("id") Long id)
	{
		 cs.claimCheck(id);
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
	
	
	
	
	
	
	
	@GetMapping("/search/{subject}")
	public ResponseEntity<Page<Claim>> findBysubject(@PathVariable("subject") String subject, @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(value = "size", defaultValue = "10", required = false) Integer size){
		Pageable pageable = PageRequest.of(page, size);
		Page<Claim> cl = cs.findBysubject(subject,pageable);	
		return ResponseEntity.ok().body(cl);
	}

	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
