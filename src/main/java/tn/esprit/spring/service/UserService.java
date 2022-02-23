package tn.esprit.spring.service;

import org.springframework.stereotype.Service;
import java.util.List;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;


@Service

public interface UserService {
	
public List<User> getAllUser();	
User addUser(User u);
void deleteUser(Long id);
User updateUser(User u);
User retrieveUser(Long id);


}
