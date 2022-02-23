package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Opportunity;
import tn.esprit.spring.entities.Travel;



public interface IOpportunityService {
	public List<Opportunity> retrieveAllOpportunities();

	Opportunity addOpportunity(Opportunity o);

	void deleteOpportunity(Long idOpportunity);

	Opportunity updateOpportunity(Opportunity o);

	Opportunity retrieveOpportunityById(Long idOpportunity);
	
	Opportunity updateOpportunityById(Opportunity o,Long idOpportunity);
}
