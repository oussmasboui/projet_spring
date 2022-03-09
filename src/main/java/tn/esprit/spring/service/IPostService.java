package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.User;

public interface IPostService {

	public List<Post> retrieveAllPost();
	
	public Post addPost(Post p, Long idUser);
	
	public void deletePost(Long idPost, Long idUser);
	
	public Post updatePost(Post p, Long id, Long idUser);
	
	public Post retrievePostById(Long idPost);
	
	public void likePost(Long idPost, long idUser);
	
	public void dislikePost(Long idPost, long idUser);
	
	public List<Object>  employeeOfTheMonth();
	
	public List<Post> filterPost(String str);
}
