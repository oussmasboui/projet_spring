package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Metrique;

@Repository
public interface MetriqueRepository extends CrudRepository<Metrique, Long>{

}
