package tn.esprit.spring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Opportunity;
import tn.esprit.spring.entities.TypeOpportunity;
import tn.esprit.spring.entities.User;

@Repository
public interface OpportunityRepository extends CrudRepository<Opportunity, Long> ,JpaRepository<Opportunity, Long> {

	



}
