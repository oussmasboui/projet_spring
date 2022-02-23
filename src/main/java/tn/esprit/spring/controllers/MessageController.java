package tn.esprit.spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Message;
import tn.esprit.spring.service.IMessageService;


@RestController 
@RequestMapping("/message") 
public class MessageController {
	
	@Autowired
	IMessageService messageService;
	
	@GetMapping("/retrieveAll") 
	@ResponseBody 
	public List<Message> retrieveAllMessage() {
		return messageService.retrieveAllMessage();
	}

	@PostMapping("/add") 
	@ResponseBody 
	public Message addMessage(@RequestBody Message m) {
		return messageService.addMessage(m);
	}

	@PostMapping("/delete/{idMessage}") 
	public void deleteMessage(@PathVariable Long idMessage) {
		messageService.deleteMessage(idMessage);
		
	}

	@PostMapping("/update/{idMessage}") 
	@ResponseBody 
	public Message updateMessage(@RequestBody Message m, @PathVariable Long idMessage) {
		return messageService.updateMessage(m, idMessage);
	}

	@GetMapping("/retrieveBy/{idMessage}") 
	@ResponseBody 
	public Optional<Message> retrieveMessageById(@PathVariable Long idMessage) {
		return messageService.retrieveMessageById(idMessage);
	}

}
