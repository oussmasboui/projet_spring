package tn.esprit.spring.service;
import java.util.List;
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
	        ur.save(u);
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
	
	
	

}
