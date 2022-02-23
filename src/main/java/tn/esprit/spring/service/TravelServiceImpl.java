package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Travel;
import tn.esprit.spring.repository.TravelRepository;
@Service
public class TravelServiceImpl implements ITravelService {

	@Autowired 
	TravelRepository travelRepo;
	@Override
	public List<Travel> retrieveAllTravels() {
		
		return (List<Travel>) travelRepo.findAll();
	}


	@Override
	public Travel addTravel(Travel t) {
		// TODO Auto-generated method stub
		return travelRepo.save(t);
	}
	

	@Override
	public void deleteTravel(Long idTravel) {
		travelRepo.deleteById(idTravel);
		
	}

	@Override
	public Travel updateTravel(Travel t) {
		
		return travelRepo.save(t);
	}

	@Override
	public Travel retrieveTravelById(Long idTravel) {

		return travelRepo.findById(idTravel).orElse(null);
	}

	@Override
	public Travel updateTravelById(Travel t,Long idTravel) {
	
		Travel found= travelRepo.findById(idTravel).orElse(null);
		found.setDestination(t.getDestination());
		found.setEndDate(t.getEndDate());
	travelRepo.saveAndFlush(found);
		return found;
	}






	
	//@Override
	//public Travel updateTravelById(Travel t,Long idTravel) {
	
		//Travel found= travelRepo.findById(idTravel).orElse(null);
		//found.setDestination(t.getDestination());
		//found.setEndDate(t.getEndDate());
	//travelRepo.saveAndFlush(found);
		//return found;
	//}

}
