package tn.esprit.spring.service;

import org.springframework.stereotype.Service;
import java.util.List;

import tn.esprit.spring.entities.Event;
import tn.esprit.spring.entities.User;


@Service
public interface EventService {
	public List<Event> getAllEvent();	
	Event addEvent(Event e);
	void deleteEvent(Long id);
	Event updateEvent(Event e);
	Event retrieveEvent(Long id);
	void addUsersToEvent(Long idEvent,Long idUser);
	void sendCancellingEmail(Event event);
	void addToCalendar(Long idevent);
}
