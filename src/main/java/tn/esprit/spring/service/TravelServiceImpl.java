package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Opportunity;
import tn.esprit.spring.entities.Travel;
import tn.esprit.spring.entities.TravelPlanning;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.mail.EmailControllers;
import tn.esprit.spring.repository.TravelPlanningRepository;
import tn.esprit.spring.repository.TravelRepository;
import tn.esprit.spring.repository.UserRepository;
@Service
public class TravelServiceImpl implements ITravelService {

	@Autowired 
	TravelRepository travelRepo;
	@Autowired 
	UserRepository userRepo;
	@Autowired 
	EmailControllers mailController;
	@Autowired 
	TravelPlanningRepository travplarepo;
	
	
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
		found.setStartDate(t.getStartDate());
		found.setEndDate(t.getEndDate());
		found.setObjet(t.getObjet());
		found.setTravelplannings(t.getTravelplannings());
		found.setUsers(t.getUsers());
	    travelRepo.saveAndFlush(found);
		return found;
	}




	@Override
	public void affecterTraveltoTraveler(Long idTravel, Long idUser) {
		//opportunityRepo.save(op);
		
		User user= userRepo.findById(idUser).orElse(null);
	//	List<TravelPlanning> tp =travplarepo.gettravelplanningsByIdTravel(t.getIdTravel());
		//System.out.println(tp);
		String mailt = user.getEmail();
		 Travel t = travelRepo.getById(idTravel);
		//if (user.getRole().toString().equals("Traveler")) 
	//	{
	user.getTravels().add(t);
		userRepo.save(user); 
	    mailController.ApplicationMail(mailt,t.getDestination(),user.getName());
		
		//}
	//	else {
			
		//	System.out.print("travel belong onely to Traveler");
		
		//}
		
		
		
		
		
		
	}


	@Override
	public List<Travel> getTravelsWithDate(Date startDate, Date endDate, String day) {
		// TODO Auto-generated method stub
		return travelRepo.gettarvelsByDateAndday(startDate, endDate, day);
	}


	@Override
	public List<Map<Long, Integer>> StatisticUserTravel() {
		// TODO Auto-generated method stub
		return travelRepo.statistics();
	}

	@Override
	public List<TravelPlanning> p(String destination){
		List<TravelPlanning> tp=new ArrayList<TravelPlanning>();
	 for(Travel t: travelRepo.findByDestination(destination)) {
		 
		 tp.addAll(t.getTravelplannings());
	 }
		return tp;
	}







	


}
