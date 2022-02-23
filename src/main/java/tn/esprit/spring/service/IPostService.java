package tn.esprit.spring.service;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.Post;

public interface IPostService {

	public List<Post> retrieveAllPost();
	
	public Post addPost(Post p);
	
	public void deletePost(Long idPost);
	
	public Post updatePost(Post p, Long id);
	
	public Optional<Post> retrievePostById(Long idPost);
}
