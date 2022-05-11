package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Travel;
import tn.esprit.spring.entities.TravelPlanning;
import tn.esprit.spring.repository.TravelPlanningRepository;
import tn.esprit.spring.repository.TravelRepository;
@Service
public class TravelPlanningServiceImpl implements ITravelPlanningService{

	@Autowired
	TravelPlanningRepository planningRepo;
	
	@Autowired
	TravelRepository travelRepo;
	
	@Override
	public List<TravelPlanning> retrieveAllTravelsPlanning() {
		// TODO Auto-generated method stub
		return planningRepo.findAll();
	}
	@Override
	public TravelPlanning addTravelPlanning(TravelPlanning tp, long idtravel) {
		Travel t = travelRepo.findById(idtravel).orElse(null);
		tp.setTravel(t);
		return planningRepo.save(tp);
	}
	@Override
	public  void deleteTravelPlanning(Long idPlanning) {
		planningRepo.deleteById(idPlanning);
		
	}
	@Override
	public void deleteAllTravelPlanning() {
		planningRepo.deleteAll();
		
	}
	@Override
	public TravelPlanning updateTravelPlanning(TravelPlanning tp) {
		
		return planningRepo.save(tp);
	}
	@Override
	public List<TravelPlanning> FindPlanningByTravelDestination(String destination) {
	return planningRepo.GetPlanningByTravelDestination(destination);
	}
	
	
	
	
	

}
