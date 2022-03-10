package tn.esprit.spring.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Claim;

@Repository

public interface ClaimRepository extends PagingAndSortingRepository<Claim, Long>{
	
	
	Page<Claim> findBysubject(String subject, Pageable pageable);
    
	
	@Query(value="SELECT * FROM CLAIM ORDER BY DATE",  nativeQuery = true)
	public List<Claim> trierParDate();
	
	
	@Query(value="SELECT * FROM CLAIM WHERE ETAT=?1", nativeQuery = true )
	public List<Claim> filterClaims(Boolean etat);
	
	
	
	
	
	
	
	/////////////
	
	
	
	/*
	
	 @Query(value ="select * from Claim m where m.etat like '%1%'  ", nativeQuery = true)
	    List<Claim> findMealByStatus1();
	  @Query(value ="select * from Claim m where m.etat like '%2%'  ", nativeQuery = true)
	    List<Claim> findMealByStatus2();
	  @Query(value ="select * from Claim m where m.etat like '%0%'  ", nativeQuery = true)
	    List<Claim> findMealByStatus0();*/
	
	
	
	
	
	
	
	
	
	
	

}
