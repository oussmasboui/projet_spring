package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.service.UserService;
@RestController

public class UserController {
	@Autowired
	UserService userservice;
	//creating a get mapping that retrieves all the user detail from the database   
	@GetMapping("/users")
	private List<User>getAllUser()
	{
		List<User> users= userservice.getAllUser();
		return users;
	}
	//creating a get mapping that retrieves the detail of a specific user 
	@GetMapping("/user/{userid}")
	private User getUser(@PathVariable("userid")Long userid)
	{
		User u= userservice.retrieveUser(userid);
		return u;
	}
	//creating a delete mapping that deletes a specified book  
	@DeleteMapping("/deleteUser/{userid}")
	private void DeleteUser(@PathVariable("userid")Long userid)
	{
		userservice.deleteUser(userid);
	}
	//creating post mapping that post the book detail in the database  
	@PostMapping("/saveUser")  
	private User saveUser(@RequestBody User user)   
	{  
	userservice.addUser(user);  
	return user;
	}  
	//creating put mapping that updates the book detail   
	@PutMapping("/updateUser")  
	private User update(@RequestBody User user)   
	{  
	userservice.updateUser(user);  
	return user;  
	}  
	
	
	

}
