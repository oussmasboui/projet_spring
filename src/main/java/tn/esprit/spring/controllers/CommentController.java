package tn.esprit.spring.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.service.ICommentService;

@RestController 
@RequestMapping("/comment") 
public class CommentController {
	
	@Autowired
	ICommentService commentService;
	@Autowired
	CommentRepository commentRepo;
	
	@GetMapping("/commentMonth") 
	@ResponseBody 
	public List<Object[]> commentMonth() {
		Date dateStart = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date dateEnd = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		return commentRepo.countTotalCommentsByMonth(dateStart, dateEnd);
	}
		
	@GetMapping("/retrieveAll") 
	@ResponseBody 
	public List<Comment> retrieveAllPost() {
		return commentService.retrieveAllComment();
	}

	@PostMapping("/add/{idPost}/{idUser}") 
	@ResponseBody 
	public Comment addComment(@RequestBody Comment c, @PathVariable Long idPost, @PathVariable Long idUser) {
		return commentService.addComment(c,idPost, idUser);
	}

	@PostMapping("/delete/{idComment}/{idUser}") 
	public void deleteComment(@PathVariable Long idComment, @PathVariable Long idUser) {
		commentService.deleteComment(idComment, idUser);
		
	}

	@PostMapping("/update/{idComment}/{idUser}") 
	@ResponseBody 
	public Comment updateMessage(@RequestBody Comment c, @PathVariable Long idComment , @PathVariable Long idUser) {
		return commentService.updateComment(c, idComment, idUser);
	}

	@GetMapping("/retrieveBy/{idComment}") 
	@ResponseBody 
	public Optional<Comment> retrieveCommentById(@PathVariable Long idComment) {
		return commentService.retrieveCommentById(idComment);
	}

}
