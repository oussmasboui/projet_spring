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

import tn.esprit.spring.entities.Post;
import tn.esprit.spring.service.IPostService;

@RestController 
@RequestMapping("/post") 
public class PostController {
	
	@Autowired
	IPostService postService;
	
	@GetMapping("/retrieveAll") 
	@ResponseBody 
	public List<Post> retrieveAllPost() {
		return postService.retrieveAllPost();
	}

	@PostMapping("/add") 
	@ResponseBody 
	public Post addPost(@RequestBody Post p) {
		return postService.addPost(p);
	}

	@PostMapping("/delete/{idPost}") 
	public void deletePost(@PathVariable Long idPost) {
		postService.deletePost(idPost);
		
	}

	@PostMapping("/update/{idPost}") 
	@ResponseBody 
	public Post updateMessage(@RequestBody Post p, @PathVariable Long idPost) {
		return postService.updatePost(p, idPost);
	}

	@GetMapping("/retrieveBy/{idPost}") 
	@ResponseBody 
	public Optional<Post> retrievePostById(@PathVariable Long idPost) {
		return postService.retrievePostById(idPost);
	}

}
