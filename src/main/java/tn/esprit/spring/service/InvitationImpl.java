package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Claim;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.repository.InvitationRepository;

@Service

public class InvitationImpl implements InvitationService  {
	@Autowired
	InvitationRepository repository;

	@Override
	public Invitation addInvitation(Invitation c) {
		// TODO Auto-generated method stub
		return  repository.save(c);
	}

	@Override
	public List<Invitation> retrieveAllInvitation() {
		// TODO Auto-generated method stub
		return (List<Invitation>) repository.findAll();
	}

	@Override
	public void deleteInvitation(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);

	}

	@Override
	public Invitation updateInvitation(Invitation c) {
		// TODO Auto-generated method stub
		return repository.save(c);
	}

	@Override
	public Invitation updateInvitationById(Invitation o, Long idInvitation) {
		// TODO Auto-generated method stub
		
		
		
		Invitation kl= repository.findById(idInvitation).orElse(null);
		
		

		kl.setState(o.isState());

	
		
		repository.saveAndFlush(kl);


		
		return kl;
			

		
		
	}
	
	
	
	
	
	
	
	

	
	
}
