package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.TravelPlanning;
import tn.esprit.spring.repository.TravelPlanningRepository;
@Service
public class TravelPlanningServiceImpl implements ITravelPlanningService{

	@Autowired
	TravelPlanningRepository planningRepo;
	@Override
	public List<TravelPlanning> retrieveAllTravelsPlanning() {
		// TODO Auto-generated method stub
		return planningRepo.findAll();
	}
	@Override
	public TravelPlanning addTravelPlanning(TravelPlanning tp) {
		// TODO Auto-generated method stub
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
