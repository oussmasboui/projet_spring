package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ch.qos.logback.core.status.Status;
import tn.esprit.spring.entities.Claim;


public interface ClaimService {
	 Claim addClaim( Claim c,Long iduser);
	 
	 
		List<Claim> retrieveAllClaim();
		void deleteClaim(Long id);
		Claim updateClaim(Claim c);
	 
		Claim updateClaimById(Claim o,Long idClaim);
		
		
		Page<Claim> findBysubject(String subject, Pageable pageable);

		void claimCheck(Long idClaim);
		
		Boolean badWords(Claim c);


		List<Claim> retrieveAllClaimpdf();
		
		public String getEtat();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		///////////////////////////
		
		
		
		
		
	
		/*List<Claim> getAllReclamationss();
		List<Claim> getAllReclamationsss();


		Status convertToEntityAttribute();
		*/
		
		
		
		
		
		
		
		
		
	 
}
