package tn.esprit.spring.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

	@Query(value="SELECT * FROM EVENT order by dateevent", nativeQuery = true)
    public List<Event> orderByDate();
	
	
	@Query(value="SELECT * FROM EVENT WHERE lieu=?1", nativeQuery = true)
    public List<Event> searchByPlace(String lieu);

}