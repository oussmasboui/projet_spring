package tn.esprit.spring.service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.InvitationRepository;
import tn.esprit.spring.repository.UserRepository;

@Service

public class UserImpl implements UserService {
	
	private final UserRepository ur;

	//BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
	
    @Autowired
	public UserImpl(UserRepository ur) {
		this.ur=ur;
	}
	@Override
	public List<User> getAllUser() {
        return (List<User>)ur.findAll();
	}

	@Override
	public User addUser(User u) {
		   String email= u.getEmail();
		   Optional<User> user= ur.findUserByEmail(email);
		   if (user.isPresent())
		   {
			   System.out.println("user already exists");
		   }
		   else
		   {  
		//	u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
			u.setBlocked(false);
	        ur.save(u);
		   }
	        return u;
	}

	
	@Override
	public void deleteUser(Long id){
		ur.deleteById(id);
	}

	@Override
	public User updateUser(User u) {
		ur.save(u);
		return u;
	}

	@Override
	public User retrieveUser(Long id) {
		User u=ur.findById(id).get();
		return u;
	}
	 @Override
	    public Optional<User> FindUserByEmail(String email) {
	        return ur.findUserByEmail(email);
	    }
	@Override
	public List<User> filterByDomain(String domain) {
		return ur.filterByDomain(domain);
	}
	@Override
	public List<User> orderByName() {
		return ur.orderByName();
	}
	@Override
	public User authenticate(String email, String password) {
	      Optional<User> u= ur.findUserByEmail(email);
	         if (u.isPresent())
	         {
	             User user= u.get();
	             if (password.equals(user.getPassword())){
	                 return user;
	             }
	         }
	         
             System.out.println("Ooops ! Try again..");

	    return null;
	}
	
	@Override
	public void blockUser(Long iduser) {

		User u= ur.findById(iduser).get();
		u.setBlocked(true);
		ur.save(u);
	}
	@Override
	public List<User> topUsers() {
		List<User> users= ur.orderByScoreEvent();
		System.out.println(users.size());
		return users;
		
	}
	
	

	
	

}
