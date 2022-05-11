package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Opportunity;

import tn.esprit.spring.service.IOpportunityService;


@RestController 
@RequestMapping("/opportunity") 
@CrossOrigin(origins = "*")
public class OpportunityRestController {

	@Autowired
  IOpportunityService opportunityservice;
	
	// http://localhost:8089/SpringMVC/opportunity/retrieve-all-opportunities 
	
	@GetMapping("/retrieve-all-opportunities") 
	@ResponseBody 
	public List<Opportunity> getOpportunity() { 
		return opportunityservice.retrieveAllOpportunities();
	  
	}
	
	//http://localhost:8089/SpringMVC/opportunity/retrieve-opportunity/2
	@GetMapping("/retrieve-opportunity/{opportunity-id}")
	@ResponseBody
	public Opportunity retrieveOpportunityById(@PathVariable("opportunity-id") Long idOpportunity) {
	return opportunityservice.retrieveOpportunityById(idOpportunity);
	}
	
	
	//http://localhost:8089/SpringMVC/opportunity/add-opportunity
	@PostMapping("add-opportunity")
	@ResponseBody
	public Opportunity AddTravel(@RequestBody Opportunity o)
	{
		return opportunityservice.addOpportunity(o);
	}
	
	
	//http://localhost:8089/SpringMVC/opportunity/remove-opportunity/{opportunity-id}
	@DeleteMapping("/remove-opportunity/{opportunity-id}")
	@ResponseBody
	public void removeOpportunity(@PathVariable("opportunity-id") Long idOpportunity) {
		opportunityservice.deleteOpportunity(idOpportunity);	
		}
	
	
	
	//http://localhost:8089/SpringMVC/opportunity/modify-opportunity
		@PutMapping("/modify-opportunity")
		@ResponseBody
		public Opportunity ModifyOpportunity(@RequestBody Opportunity o)
		{
			return opportunityservice.updateOpportunity(o);
		}

		
		
		
		//http://localhost:8089/SpringMVC/opportunity/modify-oppotunity-byID/2
		@PutMapping("/modify-oppotunity-byID/{opportunity-id}")
		@ResponseBody
		public Opportunity ModifyOpportunityById(@PathVariable("opportunity-id") Long idOpportunity,@RequestBody Opportunity o) {
		return opportunityservice.updateOpportunityById(o, idOpportunity);
		}
				
				
		@PostMapping("/assignopportunUser/{user-id}")
		@ResponseBody
		public void assignopportunitytoUser(@RequestBody Opportunity op , @PathVariable("user-id") Long idUser) {
			opportunityservice.ajouterEtaffecterOpportunitytoUser(op, idUser);
		}
}
