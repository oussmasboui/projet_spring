package tn.esprit.spring.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class EmailControllers {
	
	 @Autowired
		JavaMailSender mailSender;

	  
	  @GetMapping("/test")
		public String send(){
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("ons.chebbi@esprit.tn");
			message.setTo("aicha.nouisser@esprit.tn");
			message.setSubject("travel Planning message");
			message.setText("Has been approved !");
			mailSender.send(message);
			
			return "done";
		}
	  
	
	  public String ApplicationMail(String Mail , String dest, String name)
	  {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("asma.abessi@esprit.tn");
			message.setTo(Mail);
			message.setText("Mr/Mrs : "+name+ "\n Your flight to : " +dest+ " Has been approved !");
			message.setSubject("Traavel");
			mailSender.send(message);
		 
		    return "Successfully sent";
	  }
	  
	



}
