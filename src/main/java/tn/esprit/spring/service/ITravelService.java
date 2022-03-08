package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import tn.esprit.spring.entities.Travel;
import tn.esprit.spring.entities.TravelPlanning;



public interface ITravelService {
	public List<Travel> retrieveAllTravels();
	
	Travel addTravel(Travel t);

	void deleteTravel(Long idTravel);

	Travel updateTravel(Travel t);

	Travel retrieveTravelById(Long idTravel);
	
	Travel updateTravelById(Travel t,Long idTravel);
	
	public void affecterTraveltoTraveler(Long idTravel, Long idUser);
	
	List <Travel> getTravelsWithDate(Date startDate ,Date endDate,String day);
	List<Map<Long,Integer>> StatisticUserTravel();

	public List<TravelPlanning> p(String destination);

	public String Matching();

}
