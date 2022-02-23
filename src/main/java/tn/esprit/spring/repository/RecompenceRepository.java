package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Recompence;
@Repository
public interface RecompenceRepository extends CrudRepository<Recompence, Long>{

}
