package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Claim;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.repository.ClaimRepository;


@Service

public class  ClaimImpl implements ClaimService {
	@Autowired
	ClaimRepository repository;

	@Override
	public Claim addClaim(Claim c) {
		// TODO Auto-generated method stub
		return repository.save(c);
	}

	@Override
	public List<Claim> retrieveAllClaim() {
		// TODO Auto-generated method stub
		return (List<Claim>) repository.findAll();
	}

	@Override
	public void deleteClaim(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);

	}

	@Override
	public Claim updateClaim(Claim c) {
		// TODO Auto-generated method stub
		return repository.save(c);
	}

	@Override
	public Claim updateClaimById(Claim o, Long idClaim) {
		// TODO Auto-generated method stub
		
		Claim cl= repository.findById(idClaim).orElse(null);
		
		

cl.setDate(o.getDate());

cl.setDescription(o.getDescription());

repository.saveAndFlush(cl);



return cl;
	}
	
	
	
	
	
	

	
	
}