package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Travel;



public interface ITravelService {
	public List<Travel> retrieveAllTravels();
	
	Travel addTravel(Travel t);

	void deleteTravel(Long idTravel);

	Travel updateTravel(Travel t);

	Travel retrieveTravelById(Long idTravel);
	
	Travel updateTravelById(Travel t,Long idTravel);
}
