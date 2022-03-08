package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.User;



public interface UserRepository extends JpaRepository<User,Long> {
}
