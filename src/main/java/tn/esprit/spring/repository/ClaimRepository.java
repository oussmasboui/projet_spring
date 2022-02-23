package tn.esprit.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Claim;

@Repository

public interface ClaimRepository extends JpaRepository<Claim, Long>{

}
