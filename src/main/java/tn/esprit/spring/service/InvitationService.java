package tn.esprit.spring.service;

import java.util.List;


import tn.esprit.spring.entities.Invitation;

public interface InvitationService {
	Invitation addInvitation(Invitation c);
	List<Invitation> retrieveAllInvitation();
	void deleteInvitation(Long id);
	Invitation updateInvitation(Invitation c);
	Invitation updateInvitationById(Invitation o,Long idInvitation);
}
