package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Opportunity;
import tn.esprit.spring.entities.Travel;
import tn.esprit.spring.entities.TypeOpportunity;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.OpportunityRepository;
import tn.esprit.spring.repository.TravelRepository;
import tn.esprit.spring.repository.UserRepository;
@Service
public class OpportunityServiceImpl implements IOpportunityService {

	@Autowired 
	OpportunityRepository opportunityRepo;
	@Autowired 
	UserRepository userRepo;
	@Override
	public List<Opportunity> retrieveAllOpportunities() {
		
		return (List<Opportunity>) opportunityRepo.findAll();
	}

	@Override
	public Opportunity addOpportunity(Opportunity o) {
		
		return opportunityRepo.save(o);
	}

	@Override
	public void deleteOpportunity(Long idOpportunity) {
		opportunityRepo.deleteById(idOpportunity);
		
	}

	@Override
	public Opportunity updateOpportunity(Opportunity o) {
		
		return opportunityRepo.save(o);
	}

	@Override
	public Opportunity retrieveOpportunityById(Long idOpportunity) {

		return opportunityRepo.findById(idOpportunity).orElse(null);
	}
	
	@Override
	public Opportunity updateOpportunityById(Opportunity o,Long idOpportunity) {
	
		Opportunity opp= opportunityRepo.findById(idOpportunity).orElse(null);
		opp.setDescription(o.getDescription());
		opp.setDate(o.getDate());
		opp.setTypeo(o.getTypeo());
		opp.setUser(o.getUser());
	opportunityRepo.saveAndFlush(opp);
	return opp;
	}

	@Override
	public void ajouterEtaffecterOpportunitytoUser(Opportunity op, Long idUser) {
		//opportunityRepo.save(op);
		User user= userRepo.findById(idUser).orElse(null);
		if (user.getRole().toString().equals("Traveler")) 
		{
		op.setUser(user);
		opportunityRepo.save(op); 
		}
		else {
			System.out.print("not allowed ? ");
		}
		
	}

	
}
