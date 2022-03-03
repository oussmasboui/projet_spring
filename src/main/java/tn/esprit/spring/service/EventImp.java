package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.entities.Event;
import tn.esprit.spring.repository.EventRepository;
import java.util.Set;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.InvitationRepository;
import tn.esprit.spring.repository.UserRepository;


@Service
public class EventImp implements EventService {

	private final EventRepository er;
	
	@Autowired
	UserRepository ur;
	
	
	@Autowired
	public EventImp(EventRepository er) {
		this.er=er;
	}
	
	
	@Override
	public List<Event> getAllEvent() {
        List<Event> events = (List<Event>) er.findAll();
        return events;
        
	}

	@Override
	public Event addEvent(Event e) {
       er.save(e);
		
		return e;
	}

	@Override
	public void deleteEvent(Long id) {
		er.deleteById(id);
		
	}

	@Override
	public Event updateEvent(Event e) {
		er.save(e);
		return e;
	}

	@Override
	public Event retrieveEvent(Long id) {
		
		return er.findById(id).get();
	}


	@Override
	public void addUsersToEvent(Long idEvent, Long idUser) {
		 User u= ur.findById(idUser).get();
			
		 Event ev= er.findById(idEvent).get();
		 
		
		int maxUsers= ev.getNuminvité();

		
			ev.getUsers().add(u);
			
			ev.setNuminvité(maxUsers+1);
		
		
		er.save(ev);

		
	}
}



