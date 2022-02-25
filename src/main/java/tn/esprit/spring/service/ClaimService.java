package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Claim;


public interface ClaimService {
	 Claim addClaim( Claim c,Long iduser);
	 
	 
		List<Claim> retrieveAllClaim();
		void deleteClaim(Long id);
		Claim updateClaim(Claim c);
	 
		Claim updateClaimById(Claim o,Long idClaim);
		
	 
}
