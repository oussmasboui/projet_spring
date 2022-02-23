package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	

	

}
