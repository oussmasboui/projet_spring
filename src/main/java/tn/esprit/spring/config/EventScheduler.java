package tn.esprit.spring.config;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Event;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.service.EventService;
import tn.esprit.spring.service.UserService;

@Component
@Slf4j
public class EventScheduler {
@Autowired
EventRepository er;
@Autowired
EventService es; 
@Autowired
UserService us; 
@Autowired
JavaMailSender mailSender;

 @Transactional
 @Scheduled(cron="*/60 * * * * *") //1 fois par 60 secondes
 //@Scheduled(cron="0 0 * 1-12 SUN-SAT")  //At 00:00 on every day-of-week from Sunday through Saturday in every month from January through December.        
	  public void cancelEvent()
	   { 
	    Date today= new Date();
	    java.sql.Date sqlDate = new java.sql.Date(today.getTime()+ (1000 * 60 * 60 * 24));
	    Event event= er.getEventToCancel(sqlDate);
	    es.sendCancellingEmail(event);
	    
	   }
 
 @Transactional
 @Scheduled(cron="*/15 * * * * *")
// @Scheduled(cron="0 0 1 1-12 *") //At 00:00 on day-of-month 1 in every month from January through December.
 public void TopScore()
 { 
	 List<User> users= us.topUsers();
	  System.out.println("Our top 3 users in this month are:");
      int i=1;
     for(User user:users)
     {
    	 System.out.println(i+"- "+user.getName());
    	 i++;
     }
  
 }
 }
