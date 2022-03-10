package tn.esprit.spring.service;

import java.util.List;
import java.util.Set;

import tn.esprit.spring.entities.Invitation;

public interface InvitationService {

	void addListInvitation(List<Invitation> c);
	void addInvitation(Invitation c,List<Long> idusers);
	List<Invitation> retrieveAllInvitation();
	void deleteInvitation(Long id);
	Invitation updateInvitation(Invitation c);
	Invitation updateInvitationById(Invitation o,Long idInvitation);
	List<Invitation>findBystate(boolean state) ;
	void addUserToInvitation(Long idUser, Long idInv);
	public Set<Invitation> invitationsParUser(Long idInv);
    public void accepterInvitation(Long idInv);
    
	public String getEtat();
	public List<Float> getAllByMonth() ;

	public String EtatInvi(long id);
	
}
