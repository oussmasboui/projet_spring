package tn.esprit.spring.service;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.Comment;

public interface ICommentService {

public List<Comment> retrieveAllComment();
	
	public Comment addComment(Comment c, Long idPost);
	
	public void deleteComment(Long idComment);
	
	public Comment updateComment(Comment c, Long id);
	
	public Optional<Comment> retrieveCommentById(Long idComment);
}
