package tn.esprit.spring.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;


@Service

public interface UserService {
	
public List<User> getAllUser();	
User addUser(User u);
void deleteUser(Long id);
User updateUser(User u);
User retrieveUser(Long id);
Optional<User> FindUserByEmail(String email);
List<User> filterByDomain(String domain);
public List<User> orderByName();
public User authenticate(String email, String password);

public void blockUser(Long iduser);
public List<User> topUsers();
}
