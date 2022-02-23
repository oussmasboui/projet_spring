package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import tn.esprit.spring.entities.Opportunity;
import tn.esprit.spring.entities.Travel;
import tn.esprit.spring.service.OpportunityServiceImpl;
import tn.esprit.spring.service.TravelServiceImpl;

@RestController 
@RequestMapping("/opportunity") 
public class OpportunityRestController {

	@Autowired
  OpportunityServiceImpl opportunityservice;
	
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

		//http://localhost:8089/SpringMVC/opportunity/modify-opportunity-byID/2
		@PutMapping("/modify-opportunity-byID/{opportunity-id}")
		@ResponseBody
		public Opportunity modifyOpportunityById(@PathVariable("opportunity-id") Long idOpportunity,@RequestBody Opportunity o) {
			return 
					opportunityservice.updateOpportunityById(o, idOpportunity);
		}
		
}
