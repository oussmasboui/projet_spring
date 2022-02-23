package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import tn.esprit.spring.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
