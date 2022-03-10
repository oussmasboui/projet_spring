package tn.esprit.spring.repository;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;






@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Transactional
	@Query(value="select * from user where email=?1 ", nativeQuery = true)
	public Optional<User>findUserByEmail(String email);
	
	@Transactional
	@Query(value="select * from user order by score_events DESC LIMIT 3", nativeQuery = true)
	public List<User> orderByScoreEvent();
	
	@Transactional
	@Query(value="select * from user  where domain=?1", nativeQuery = true)
	public List<User> filterByDomain(String domain);
	@Transactional
	@Query(value="select * from user order by name", nativeQuery = true)
	public List<User> orderByName();
	@Transactional
	@Query(value="select * from user where email=?1 and password=?2", nativeQuery = true)
	public Optional<User> authenticate(String email, String password);
	
	
}
