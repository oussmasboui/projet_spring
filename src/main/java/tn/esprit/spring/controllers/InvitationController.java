package tn.esprit.spring.controllers;

import java.util.List;
import java.util.Set;


import org.springframework.ui.Model;




import com.google.zxing.WriterException;



import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.vonage.client.VonageClient;


import tn.esprit.spring.config.QRCodeGenerator;
import tn.esprit.spring.entities.Claim;
import tn.esprit.spring.entities.Event;
import tn.esprit.spring.entities.Invitation;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.mail.MailConfig;
import tn.esprit.spring.mail.MailConfiguration;
import tn.esprit.spring.repository.InvitationRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.InvitationService;

import java.io.IOException;
import java.util.Base64;
@RestController
@RequestMapping("/Invitation")
public class InvitationController {
	@Autowired 
	InvitationService cs;
	@Autowired
	MailConfig mailConf;
	
     
	 @Autowired
	 UserRepository ur;
	
	 
	 @Autowired
	 InvitationService inv;
	 
	 @Autowired
	 InvitationRepository invRepo;
	 private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";



		@GetMapping(value="/mois")
		@ResponseBody	
		public List<Float> getChiffreAffaireForAllMonth()
		{
			
			return (List<Float>)inv.getAllByMonth();
		}
		
	 
	 @GetMapping(value = "/genrateQRCode/{idinvi}")
	    public String getQRCode( Model model,@PathVariable("idinvi") long id){
	    	  Invitation invi=invRepo.findById(id).get();
	      	//String a="libelle"+tock.getLibelleStock().toString();
	        String medium="Date Invitation :"+invi.getDateinvitation()+"\nLocation :"+invi.getLocation();
	        
	        
	        
	        String github="libelle :"+invi.getDateinvitation();

	        byte[] image = new byte[0];
	        try {

	            // Generate and Return Qr Code in Byte Array
	            image = QRCodeGenerator.getQRCodeImage(medium,250,250);

	            // Generate and Save Qr Code Image in static/image folder
	            QRCodeGenerator.generateQRCodeImage(medium,250,250,QR_CODE_IMAGE_PATH);

	        } catch (WriterException | IOException e) {
	            e.printStackTrace();
	        }
	        // Convert Byte Array into Base64 Encode String
	        String qrcode = Base64.getEncoder().encodeToString(image);

	        model.addAttribute("medium",medium);
	        model.addAttribute("github",github);
	        model.addAttribute("qrcode",qrcode);

	        return "medium";
	    }
	   
	
	//@PostMapping("/addInvitation")
	//@ResponseBody
	/*public void addInvitation(@RequestBody Invitation c,@RequestParam(value="idUsers") List<Long> idUsers)
	{
		
		
		cs.addInvitation(c, idUsers);
		/*
		for(Long id:idUsers){
			User u= ur.findById(id).get();
			/*SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("aicha.nouisser@esprit.tn");
			message.setTo(u.getEmail());
			message.setSubject("This is an invitation");
			message.setText("hggjjhkjkji");
			mailSender.send(message);*/
	
	
	
	@PostMapping("/addListInvitations")
	public void ajouterListeInvitations(@RequestBody List<Invitation> inv){
		cs.addListInvitation(inv);
	}
	
	
	@PostMapping("/addUserToInv/{idUser}/{idInv}")
	@ResponseBody
	public void addUserToInv(@PathVariable("idUser") Long idUser ,@PathVariable("idInv") Long idInv)
	{
		cs.addUserToInvitation(idUser, idInv);
	mailConf.send("aicha.nouisser@esprit.tn","aichanouisser123@gmail.com", "email");
	}
	
	@PostMapping("/accepterInv/{idInv}")
	@ResponseBody
	public void accepterInv(@PathVariable("idInv") Long idInv )
	{
		cs.accepterInvitation(idInv);
		
	}
	
	@GetMapping("/get-invitation-by-user/{idUser}")
	@ResponseBody
	public Set<Invitation> getInvitationByUser(@PathVariable("idUser") Long id)
	{
		return cs.invitationsParUser(id);
	}
	
	
	
	@GetMapping("/get-all-Invitation")
	@ResponseBody
	public List<Invitation> getAllInvitation()
	{
		List<Invitation> listInvitation = cs.retrieveAllInvitation();	
		return listInvitation; 	
	}
	
	@GetMapping("/stat/invitation")
	@ResponseBody
	public String getStatInvi()
	{
		return inv.getEtat();
	}
	
	
	
	
	@DeleteMapping("/remove-Invitation/{idInvitation}")
	@ResponseBody
	public void removeInvitation(@PathVariable("idInvitation") Long idInvitation) {
		cs.deleteInvitation(idInvitation);
	}
  	
  	
	@PutMapping("/modify-Invitation")
	@ResponseBody
	public Invitation modifyInvitation(@RequestBody Invitation c)
	{
		return cs.updateInvitation(c);
	}
	
	
	
	
	
	@PutMapping("/modify-Invitation-byID/{id}")
	@ResponseBody
	public Invitation ModifyInvitation(@PathVariable("id") Long idInvitation,@RequestBody Invitation t) {
	return cs.updateInvitationById(t, idInvitation);
	
	}
	
	
	
	@GetMapping("/FindByState/{state}")
	List<Invitation>findByState(@PathVariable("state") boolean state) 
	{
		return cs.findBystate(state);
	
}
	




	


	@PostMapping("/addInvitation")
	@ResponseBody
	public void addInvitation(@RequestBody Invitation c,@RequestParam(value="idUsers") List<Long> idUsers)
	{
		
		
		cs.addInvitation(c, idUsers);}
	
	
	/*
	@PutMapping("/addInvitation")
	public ResponseEntity<Invitation> addInvitation(@RequestBody Invitation c,@RequestParam(value="idUsers") List<Long> idUsers) {

		
		 VonageClient client = VonageClient.builder().apiKey("e4bce3a2").apiSecret("PuKw4KSEoDUoaIaE").build();

		 com.vonage.client.sms.messages.TextMessage message = new com.vonage.client.sms.messages.TextMessage("Vonage APIs",
			        "21625813773",
			        "Votre invitation à été bien confirmé le '+new Date()'+"
			);


		 com.vonage.client.sms.SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

		 if (response.getMessages().get(0).getStatus() == com.vonage.client.sms.MessageStatus.OK) {
		     System.out.println("Message sent successfully.");
		 } else {
		     System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
		 }

		 cs.addInvitation(c,idUsers);
		return ResponseEntity.ok().body(c);

	}
/*
	@PostMapping("/add-bach")
	public ResponseEntity<?> addBachInvitation() throws Exception {
		batchLauncher.run();
		return new ResponseEntity<>("Okay", HttpStatus.OK);
	}*/
}
