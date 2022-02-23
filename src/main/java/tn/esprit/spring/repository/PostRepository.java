package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import tn.esprit.spring.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
