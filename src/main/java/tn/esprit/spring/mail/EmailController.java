package tn.esprit.spring.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

  @Autowired
	JavaMailSender mailSender;

 
  @GetMapping("/testmail")
	public String send(){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("aicha.nouisser@esprit.tn");
		message.setTo("ahmed.nebli@esprit.tn");
		message.setSubject("sukkkkkkkkkk");
		message.setText("jjjjjjjjjjjjjj");
		mailSender.send(message);
		return "done";
	}

}
