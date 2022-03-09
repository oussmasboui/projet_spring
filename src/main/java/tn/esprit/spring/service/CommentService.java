package tn.esprit.spring.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.PostRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class CommentService implements ICommentService{
	
	@Autowired 
	CommentRepository commentRepository;
	@Autowired 
	PostRepository postRepository;
	@Autowired 
	UserRepository userRepository;

	@Override
	public List<Comment> retrieveAllComment() {
		return (List<Comment>) commentRepository.findAll();
	}

	@Override
	public Comment addComment(Comment c, Long idPost, Long idUser) {
		Post post = postRepository.getById(idPost);
		User user = userRepository.getById(idUser);
		c.setPost(post);
		c.setUser(user);
		c.setDateComment(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		return commentRepository.save(c);
			
	}

	@Override
	public void deleteComment(Long idComment,Long idUser) {
		Comment comment = commentRepository.getById(idComment);
		if(comment.getUser().getIdUser()==idUser || comment.getPost().getUser().getIdUser()==idUser)
		{
		commentRepository.deleteById(idComment);
		}
	}

	@Override
	public Comment updateComment(Comment c, Long id, Long idUser) {
		Comment commentMain = commentRepository.getById(id);
		if(commentMain.getUser().getIdUser()==idUser || commentMain.getPost().getUser().getIdUser()==idUser)
		{
		Comment comment = commentRepository.getById(id);
		comment.setText(c.getText());
		
		return commentRepository.save(comment);
		}else{
			return commentMain;
		}
	}

	@Override
	public Optional<Comment> retrieveCommentById(Long idComment) {
		return commentRepository.findById(idComment);
	}
	
	public List<Object[]> commentMonth() {
		Date dateStart = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date dateEnd = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		return commentRepository.countTotalCommentsByMonth(dateStart, dateEnd);
	}

}
