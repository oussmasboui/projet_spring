package tn.esprit.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.repository.PostRepository;

@Service
public class PostService implements IPostService {
	
	@Autowired 
	PostRepository postRepository;

	@Override
	public List<Post> retrieveAllPost() {
		return (List<Post>) postRepository.findAll();
	}

	@Override
	public Post addPost(Post p) {
		
		return postRepository.save(p);
	}

	@Override
	public void deletePost(Long idPost) {
		postRepository.deleteById(idPost);
		
	}

	@Override
	public Post updatePost(Post p, Long id) {
		Post post = postRepository.getById(id);
		post.setContent(p.getContent());
		post.setSubject(p.getSubject());
		post.setLikes(p.getLikes());
		post.setDislikes(p.getDislikes());
		post.setViews(p.getViews());
		
		return postRepository.save(post);
		
	}

	@Override
	public Optional<Post> retrievePostById(Long idPost) {
		return postRepository.findById(idPost);
	}

}
