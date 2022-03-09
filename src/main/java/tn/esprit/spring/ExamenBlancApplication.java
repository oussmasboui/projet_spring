package tn.esprit.spring;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;



@EnableAspectJAutoProxy
@EnableScheduling
@SpringBootApplication
public class ExamenBlancApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ExamenBlancApplication.class, args);
		
	}

	

	}
		
	
	

