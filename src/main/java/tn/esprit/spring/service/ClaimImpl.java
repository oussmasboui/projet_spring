package tn.esprit.spring.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import ch.qos.logback.core.status.Status;
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
	public List<Claim> retrieveAllClaimpdf() {
		

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
cl.setSubject(o.getSubject());
//repository.saveAndFlush(cl);



return cl;
	}

	@Override
	public Claim addClaim(Claim c, Long iduser) {
		
		User u= ur.findById(iduser).get();
		c.setUser(u);
		c.setEtat(false);
		if(this.badWords(c))
		{
		repository.save(c);
		} else 
			System.out.println("non ajoutée");
		return c;
	}

	@Override
	public Page<Claim> findBysubject(String subject, Pageable pageable) {
		// TODO Auto-generated method stub
		return repository.findBysubject(subject, pageable);
	}

	@Override
	public void claimCheck(Long idClaim) {

           Claim c=repository.findById(idClaim).get();	
           c.setEtat(true);
           repository.save(c);
	}

	@Override
	public Boolean badWords(Claim c) {
		String description = c.getDescription();
		 List<String >bw= new ArrayList<String>();
		 bw.add("fuck");
		 bw.add("shit");

         for(String i:bw)
         {
        	 if (description.contains(i)){
        		 return false;
        	 }
         }
        	 
		return true;
	}

	

	//@Override
	//public Page<Claim> findBysubject(String subject, Pageable pageable) {
		// TODO Auto-generated method stub
	//	return repository.findBysubject(subject,pageable);
	
 
public List<Claim> listAll() {
    return (List<Claim>) repository.findAll();
}
@Override
public String getEtat() {
    List<Claim> lisclaim =  (List<Claim>) repository.findAll();
    int t = 0;
    int f = 0;
    for(Claim cl : lisclaim)
    {
    	if(cl.getEtat()==true)
    	{
    		t=t+1;
    	}
    	else{
    		f=f+f;
    	}
    }
    
    return "Nbre Claim Traité sont :"+t+"\nNbre Claim traité sont :"+f;
	
}














	
}


	
	
