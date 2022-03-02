package tn.esprit.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query("select u from user u where u.email=?1 ")
	public Optional<User>findUserByEmail(String email);
	
	
	@Query("select u from user u where u.domain=?1")
	public List<User> filterByDomain(String domain);
	
	@Query("select u from user u order by name")
	public List<User> orderByName();
	
	@Query("select u from user u where u.email=?1 and u.password=?2")
	public Optional<User> authenticate(String email, String password);
	
	
}
