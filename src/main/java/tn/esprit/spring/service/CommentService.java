package tn.esprit.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.PostRepository;

@Service
public class CommentService implements ICommentService{
	
	@Autowired 
	CommentRepository commentRepository;
	@Autowired 
	PostRepository postRepository;

	@Override
	public List<Comment> retrieveAllComment() {
		return (List<Comment>) commentRepository.findAll();
	}

	@Override
	public Comment addComment(Comment c, Long idPost) {
		Post post = postRepository.getById(idPost);
		c.setPost(post);
		return commentRepository.save(c);
			
	}

	@Override
	public void deleteComment(Long idComment) {
		commentRepository.deleteById(idComment);
		
	}

	@Override
	public Comment updateComment(Comment c, Long id) {
		Comment comment = commentRepository.getById(id);
		comment.setText(c.getText());
		
		return commentRepository.save(comment);
	}

	@Override
	public Optional<Comment> retrieveCommentById(Long idComment) {
		return commentRepository.findById(idComment);
	}

}
