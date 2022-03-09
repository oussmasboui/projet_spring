package tn.esprit.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Message;
import tn.esprit.spring.repository.MessageRepository;


@Service
public class MessageService implements IMessageService{
	
	@Autowired 
	MessageRepository messageRepository;

	@Override
	public List<Message> retrieveAllMessage() {
		return (List<Message>) messageRepository.findAll();
	}

	@Override
	public Message addMessage(Message m) {
		return messageRepository.save(m);
	}

	@Override
	public void deleteMessage(Long idMessage) {
		messageRepository.deleteById(idMessage);
		
	}

	@Override
	public Message updateMessage(Message m, Long id) {
		
		Message message = messageRepository.getById(id);
		message.setMessage(m.getMessage());
		
		return messageRepository.save(message);
	}

	@Override
	public Optional<Message> retrieveMessageById(Long idMessage) {
		return messageRepository.findById(idMessage);
	}

}
