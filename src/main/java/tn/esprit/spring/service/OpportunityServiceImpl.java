package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Opportunity;
import tn.esprit.spring.entities.Travel;
import tn.esprit.spring.repository.OpportunityRepository;
import tn.esprit.spring.repository.TravelRepository;
@Service
public class OpportunityServiceImpl implements IOpportunityService {

	@Autowired 
	OpportunityRepository opportunityRepo;
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
		// TODO Auto-generated method stub
		Opportunity found= opportunityRepo.findById(idOpportunity).orElse(null);
		found=opportunityRepo.save(o);
		return found;
	}

}
