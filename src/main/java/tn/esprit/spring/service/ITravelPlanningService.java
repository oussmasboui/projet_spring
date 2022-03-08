package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Opportunity;
import tn.esprit.spring.entities.TravelPlanning;

public interface ITravelPlanningService {

	public List<TravelPlanning> retrieveAllTravelsPlanning();
	 TravelPlanning addTravelPlanning(TravelPlanning tp);
	 void deleteTravelPlanning(Long idPlanning);
	 void deleteAllTravelPlanning();
	 TravelPlanning updateTravelPlanning(TravelPlanning tp);
	 public List<TravelPlanning> FindPlanningByTravelDestination(String destination);
	 
	
}
