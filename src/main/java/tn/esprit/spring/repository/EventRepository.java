package tn.esprit.spring.repository;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

	@Query(value="SELECT * FROM EVENT order by datedebut", nativeQuery = true)
    public List<Event> orderByDate();
	
	@Query(value="SELECT * FROM EVENT where datedebut=?1 and num_inv<min_inv", nativeQuery = true)
    public Event getEventToCancel(Date date);
	
	
	@Query(value="SELECT * FROM EVENT WHERE lieu=?1", nativeQuery = true)
    public List<Event> searchByPlace(String lieu);

}