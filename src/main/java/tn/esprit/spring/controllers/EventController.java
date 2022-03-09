package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.calendar.CalendarService;
import tn.esprit.spring.entities.Event;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.service.EventService;


@RestController

public class EventController {
	@Autowired
	EventService eventservice;
	
	@Autowired
	CalendarService cs;
	
	@Autowired
	EventRepository er;
	//creating a get mapping that retrieves all the user detail from the database   
		@GetMapping("/Events")
		private List<Event>getAllEvent()
		{
			List<Event> Events= eventservice.getAllEvent();
			return Events;
		}
		@GetMapping("/orderByDate")
		private List<Event> orderByDate()
		{
			List<Event> Events= er.orderByDate();
			return Events;
		}
		
		@GetMapping("/filterEventsByPlace/{place}")
		private List<Event> filterEventsByPlace(@PathVariable("place") String place)
		{
			List<Event> Events= er.searchByPlace(place);
			return Events;
		}
		//creating a get mapping that retrieves the detail of a specific event 
		@GetMapping("/Event/{idevent}")
		private Event getEvent(@PathVariable("idevent")Long idevent)
		{
			Event e= eventservice.retrieveEvent(idevent);
			return e;
		}
		//creating a delete mapping that deletes a specified Event  
		@DeleteMapping("/deleteEvent/{idevent}")
		private void DeleteEvent(@PathVariable("idevent")Long idevent)
		{
			eventservice.deleteEvent(idevent);
		}
		
		
		//creating post mapping that post the book detail in the database  
		@PostMapping("/saveEvent")  
		private Event saveEvent(@RequestBody Event event)   
		{  
		eventservice.addEvent(event); 
		Long id= event.getIdevent();
		eventservice.addToCalendar(id);
		return event;
		}
		
		
		@PostMapping("/addToCalendar/{id}")  
		private void addToCalendar(@PathVariable("id") Long id)   
		{  
		
		eventservice.addToCalendar(id);
		}
		
		
		
		
		@PostMapping("/addUserToEvent/{idevent}/{iduser}")  
		private void addUserToEvent(@PathVariable("idevent") Long idevent, @PathVariable("iduser") Long iduser)   
		{  
		  eventservice.addUsersToEvent(idevent, iduser);
		}
		/*
		@PostMapping("/mail/{idevent}")  
		private void mail(@PathVariable("idevent") Long idevent)   
		{  
		  eventservice.sendCancellingEmail(idevent);
		}*/
		//creating put mapping that updates the book detail   
		@PutMapping("/updateEvent")  
		private Event update(@RequestBody Event event)   
		{  
		eventservice.updateEvent(event);  
		return event;  
		}  
	

}
