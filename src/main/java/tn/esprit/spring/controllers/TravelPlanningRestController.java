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

import tn.esprit.spring.entities.Opportunity;
import tn.esprit.spring.entities.TravelPlanning;
import tn.esprit.spring.service.ITravelPlanningService;


@RestController 
@RequestMapping("/travelplanning") 
public class TravelPlanningRestController {
	@Autowired
	ITravelPlanningService planningService;
	
	 // http://localhost:8089/SpringMVC/travelplanning/retrieve-all-plannings
	 @GetMapping("/retrieve-all-plannings")
	 @ResponseBody
	 public List<TravelPlanning> getPlannings() {
		 return planningService.retrieveAllTravelsPlanning();
	 }
	 
	 
	// http://localhost:8089/SpringMVC/travelplanning/add-travelplanning
	 @PostMapping("/add-travelplanning")
	 @ResponseBody
	 public TravelPlanning addTravelPlanning(@RequestBody TravelPlanning tp) 
	{
		return planningService.addTravelPlanning(tp);
	 }
	 
	// http://localhost:8089/SpringMVC/travelplanning/removePlanning/{planning-id}
	 @DeleteMapping("/removePlanning/{planning-id}")
	 @ResponseBody
	 public void removeTravelPlanning(@PathVariable("planning-id") Long idPlanning) {
	 planningService.deleteTravelPlanning(idPlanning);
	 }

	 
	// http://localhost:8089/SpringMVC/travelplanning/removeAllPlannings
		 @DeleteMapping("/removeAllPlannings")
		 @ResponseBody
		 public void removeAllTravelsPlanning() {
	planningService.deleteAllTravelPlanning();
		 }
		 
		//http://localhost:8089/SpringMVC/travelplanning/modify-Plannings
			@PutMapping("/modify-Plannings")
			@ResponseBody
			public TravelPlanning ModifyPlannings(@RequestBody TravelPlanning tp)
			{
				return planningService.updateTravelPlanning(tp);
			}
			
			@GetMapping("/GetplanningByTravelDestination/{destination}") 
			@ResponseBody 
			public List<TravelPlanning> GetplanningByTravelDestination(@PathVariable("destination") String destination) { 
				return planningService.FindPlanningByTravelDestination(destination);
			} 
			

		
			
}
