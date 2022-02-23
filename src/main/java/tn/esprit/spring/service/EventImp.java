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


@Service
public class EventImp implements EventService {

	private final EventRepository er;
	
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

}
