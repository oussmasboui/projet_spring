package tn.esprit.spring.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import tn.esprit.spring.calendar.CalendarService;
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
	JavaMailSender mailSender;
	
	@Autowired
	CalendarService cs;
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
		 
		
		int number= ev.getNumInv();

		
			ev.getUsers().add(u);
			
			ev.setNumInv(number+1);
		
		
		er.save(ev);

		
	}


	@Override
	public void sendCancellingEmail(Event event) {
		Set<User> users= event.getUsers();
		    System.out.println(event);
		   for (User user : users) 
		    {
			    System.out.println(user.getEmail());
       
		    
		    SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("oussama.sboui@esprit.tn");
			message.setTo(user.getEmail());
		    message.setText("Mr/Mrs : "+user.getName()+ "/n The  : " +event.getEntrepriseName()+ " is so sad to inform you that"
					+ "the event " + event.getSubject() + "has been cancelled!"
							+ "/n But don't worry! This will add 5 points to your score!");
			message.setSubject("Tomrrow's Event has been cancelled");
			
			mailSender.send(message);
		    System.out.println("the event to cancel is"+event);
		    int eventscore= user.getScoreEvents() ;
		    System.out.println(eventscore);
			user.setScoreEvents(eventscore+5);
			
			ur.save(user);
		    er.delete(event);
		    System.out.println("event deleted"+event);
		    }
		
	}


	@Override
	public void addToCalendar(Long idevent) {

		Event event= er.findById(idevent).get();
		try {
			cs.addEventToCalendar(event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



