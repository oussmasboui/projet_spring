package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Claim;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.InvitationRepository;
import tn.esprit.spring.repository.UserRepository;


@Service

public class InvitationImpl implements InvitationService  {
	@Autowired
	InvitationRepository repository;
	@Autowired
	UserRepository ur;
///	@Autowired
	///EmailSenderService email = new EmailSenderService();

	//@Override
	//public Invitation addInvitation(Invitation c) {
		//// TODO Auto-generated method stub
		//return  repository.save(c);
	//}

	@Override
	public List<Invitation> retrieveAllInvitation() {
		// TODO Auto-generated method stub
		return (List<Invitation>) repository.findAll();
	}
	
	@Override
	public void addListInvitation(List<Invitation> c) {
		//Date date = new Date();
		//String strDateFormat = "hh:mm:ss a";
	    //DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    //String formattedDate= dateFormat.format(date);
		
		
		for (Invitation inv : c) {
			
			inv.setState(false);
			
			repository.save(inv);
			
		}
		
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
/*
	@Override
	public Invitation addInvitation(Invitation c, List<Long> idusers) {
		
		User u= ur.findById(iduser).get();
		List<User> users= new ArrayList<User>();
		c.getUsers().
		c.setUsers(users);
         
		repository.save(c);
	
		///email.sendSimpleMessage("mohamedkhalil.guedria@esprit.tn","Hello", "Content");
		
		
		
		return c;
	}
*/
	@Override
	public List<Invitation> findBystate(boolean state) {		
		return repository.findBystate(state);
	}

	@Override
	@Transactional
	public void addInvitation(Invitation c, List<Long> idusers) {
		repository.save(c);
		
		List<User> users= (List<User>) ur.findAll();
		
		for (User user : users) {
			for (Long id : idusers) {
				if (user.getIdUser()==id) {
					User us= ur.findById(id).get();
					user.getInvitations().add(c);
				}
			}
		}
		
		
	}
	
	@Override
	public String getEtat() {
	    List<Invitation> listinvitations =  (List<Invitation>) repository.findAll();
	    int t = 0;
	    int f = 0;
	    for(Invitation cl : listinvitations)
	    {
	    	if(cl.isState()==true)
	    	{
	    		
	    		
	    		t=t+1;
	    	}
	    	else{
	    		f=f+1;
	    	}
	    	System.err.print(t);
	    	System.out.print(t);
	    }
	    
	    return "Nbre Invitation accepté :"+t+"\n Nbre Invitation Réfusé :"+f;
		
	}

	@Override
	public void addUserToInvitation(Long idUser, Long idInv) {
        System.out.println(idUser);
		User u= ur.findById(idUser).get();
		System.out.println(u);
		Invitation inv= repository.findById(idInv).get();
		u.getInvitations().add(inv);
		ur.save(u);
	}

	@Override
	public Set<Invitation> invitationsParUser(Long idUser) {
		
		return ur.findById(idUser).get().getInvitations();
	}

	@Override
	public void accepterInvitation(Long idInv) {
		Invitation inv= repository.findById(idInv).get();
		inv.setState(true);
		repository.save(inv);
	}
	
	public String EtatInvi(long id)
	{
		Invitation invi = repository.findById(id).get();
		
		if(invi.isState()==true)
		{
			return "L'Invitation est : Accépté";

		}
		else {
			return "L'Invitation est : Réfusé";

		}
	}

	@Override
	public List<Float> getAllByMonth() {
		List<Float> listF = new ArrayList<Float>();
		for(int i=1;i<11;i++)
		{
			
			
			
			
		//	System.err.println(k);
			listF.add(repository.getByMonth(i));
			
			
		}
		
		//System.err.println(listF.toString());
		return listF;
	}


	
	// stat invitation 
	
	
	
	
	
	

	
	
}
