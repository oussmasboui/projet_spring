package tn.esprit.spring.mail;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

  @Bean
  JavaMailSender mailSender(){
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    mailSender.setUsername("aicha.nouisser@esprit.tn");
	    mailSender.setPassword("aicha123456789");

	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    return mailSender;
	  }
  
  
  
  
  
  
  
  
  /* JavaMailSender mailSender(){
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.mailtrap.io");
    mailSender.setPort(587);
    mailSender.setUsername("98146b2d4c71f3");
    mailSender.setPassword("35bf5b40219554");

   // Properties props = mailSender.getJavaMailProperties();
    //props.put("mail.smtp.auth", "true");
    //props.put("mail.smtp.starttls.enable", "true");
    //props.put("mail.debug", "true");
    return mailSender;
  }*/
  
}
