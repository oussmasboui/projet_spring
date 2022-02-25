package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Claim;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.ClaimRepository;
import tn.esprit.spring.repository.UserRepository;


@Service

public class  ClaimImpl implements ClaimService {
	@Autowired
	ClaimRepository repository;

	@Autowired
	UserRepository ur;


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

	@Override
	public Claim addClaim(Claim c, Long iduser) {
		
		User u= ur.findById(iduser).get();
		c.setUser(u);
		repository.save(c);
		
		return c;
	}
	
	
	
	
	
	

	
	
}