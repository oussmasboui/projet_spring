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

import tn.esprit.spring.entities.Event;
import tn.esprit.spring.service.EventService;
@RestController

public class EventController {
	@Autowired
	EventService eventservice;
	//creating a get mapping that retrieves all the user detail from the database   
		@GetMapping("/Events")
		private List<Event>getAllEvent()
		{
			List<Event> Events= eventservice.getAllEvent();
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
		return event;
		}
		//creating put mapping that updates the book detail   
		@PutMapping("/updateEvent")  
		private Event update(@RequestBody Event event)   
		{  
		eventservice.updateEvent(event);  
		return event;  
		}  
	

}
