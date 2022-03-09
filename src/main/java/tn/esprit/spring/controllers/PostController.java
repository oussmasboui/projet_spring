package tn.esprit.spring.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.DislikessRepository;
import tn.esprit.spring.repository.LikessRepository;
import tn.esprit.spring.repository.PostRepository;
import tn.esprit.spring.service.IPostService;

@RestController 
@RequestMapping("/post") 
public class PostController {
	
	@Autowired
	PostRepository postRepo;
	@Autowired
	LikessRepository likeRepo;
	@Autowired
	DislikessRepository dislikeRepo;
	
	@Autowired
	IPostService postService;
	
	@GetMapping("/retrieveAll") 
	@ResponseBody 
	public List<Post> retrieveAllPost() {
		return postService.retrieveAllPost();
	}
	
	@GetMapping("/employeeOfTheMonth") 
	@ResponseBody 
	public List<Object> employeeOfTheMonth() {
		return postService.employeeOfTheMonth();
	}
	
	@GetMapping("/filter") 
	public List<Post> filter(@RequestParam(name = "str") String str) {
		return postService.filterPost(str);
	}
	
	@GetMapping("/postMonth") 
	@ResponseBody 
	public List<Object[]> postMonth() {
		Date dateStart = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date dateEnd = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		return postRepo.countTotalPostsByMonth(dateStart, dateEnd);
	}
	
	@GetMapping("/likesMonth") 
	@ResponseBody 
	public List<Object[]> likesMonth() {
		Date dateStart = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date dateEnd = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		return likeRepo.countTotalLikesByMonth(dateStart, dateEnd);
	}
	
	@GetMapping("/dislikesMonth") 
	@ResponseBody 
	public List<Object[]> dislikesMonth() {
		Date dateStart = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date dateEnd = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		return dislikeRepo.countTotalDislikesByMonth(dateStart, dateEnd);
	}

	@PostMapping("/add/{idUser}") 
	@ResponseBody 
	public Post addPost(@RequestBody Post p, @PathVariable Long idUser) {
		return postService.addPost(p, idUser);
	}

	@PostMapping("/delete/{idPost}/{idUser}") 
	public void deletePost(@PathVariable Long idPost,@PathVariable Long idUser) {
		postService.deletePost(idPost, idUser);
		
	}

	@PostMapping("/update/{idPost}/{idUser}") 
	@ResponseBody 
	public Post updateMessage(@RequestBody Post p, @PathVariable Long idPost, @PathVariable Long idUser) {
		return postService.updatePost(p, idPost, idUser);
	}

	@GetMapping("/retrieveBy/{idPost}") 
	@ResponseBody 
	public Post retrievePostById(@PathVariable Long idPost) {
		 return postService.retrievePostById(idPost);
	}
	
	@PostMapping("/like/{idPost}/{idUser}") 
	@ResponseBody 
	public void likePost(@PathVariable Long idPost, @PathVariable Long idUser) {
		 postService.likePost(idPost, idUser);
	}
	
	@PostMapping("/dislike/{idPost}/{idUser}") 
	@ResponseBody 
	public void dislikePost(@PathVariable Long idPost, @PathVariable Long idUser) {
		 postService.dislikePost(idPost, idUser);
	}

}
