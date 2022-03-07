package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import tn.esprit.spring.entities.TravelPlanning;
@Repository
public interface TravelPlanningRepository extends CrudRepository<TravelPlanning, Long> ,JpaRepository<TravelPlanning, Long> {


	@Query("SELECT DISTINCT f FROM TravelPlanning f join f.travel u WHERE (u.destination LIKE :destination)")
	List<TravelPlanning> GetPlanningByTravelDestination(@Param("destination") String destination);
	
	
}
