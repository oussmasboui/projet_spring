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

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.service.ICommentService;

@RestController 
@RequestMapping("/comment") 
public class CommentController {
	
	@Autowired
	ICommentService commentService;
	
	@GetMapping("/retrieveAll") 
	@ResponseBody 
	public List<Comment> retrieveAllPost() {
		return commentService.retrieveAllComment();
	}

	@PostMapping("/add/{idPost}") 
	@ResponseBody 
	public Comment addComment(@RequestBody Comment c, @PathVariable Long idPost) {
		return commentService.addComment(c,idPost);
	}

	@PostMapping("/delete/{idComment}") 
	public void deleteComment(@PathVariable Long idComment) {
		commentService.deleteComment(idComment);
		
	}

	@PostMapping("/update/{idComment}") 
	@ResponseBody 
	public Comment updateMessage(@RequestBody Comment c, @PathVariable Long idComment) {
		return commentService.updateComment(c, idComment);
	}

	@GetMapping("/retrieveBy/{idComment}") 
	@ResponseBody 
	public Optional<Comment> retrieveCommentById(@PathVariable Long idComment) {
		return commentService.retrieveCommentById(idComment);
	}

}
