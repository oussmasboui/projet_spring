package tn.esprit.spring.controllers;

import java.util.List;
import java.util.Set;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Recompence;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.service.RecompenceService;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Recompence")
@Api(tags = "Recompence")
public class RecompenceController {
	@Autowired
	RecompenceService RecompenceService ;
	
	

	@GetMapping("/retrieve-all-Recompence")
	@ResponseBody
	public List<Recompence> getRecompence() {
		return RecompenceService.retrieveAllRecompence();

	}
	
	@GetMapping("/retrieve-Recompence/{Recompence-id}")
	@ResponseBody
	public Recompence retrieveRecompence(@PathVariable("Recompence-id") Long idRecompence) {
		return RecompenceService.retrieveRecompence(idRecompence);
	}

	@PostMapping("/add-Recompence/{id}")
	@ResponseBody
	public Recompence addProduct(@RequestBody Recompence r,@PathVariable() Long id) {
		return RecompenceService.addRecompence(r,id);
	}
	@DeleteMapping("/remove-Recompence/{Recompence-id}")
	@ResponseBody
	public void removeRecompence(@PathVariable("Recompence-id") Long idRecompence) {
		RecompenceService.deleteRecompence(idRecompence);
	}

	@PutMapping("/modify-Recompence")
	@ResponseBody
	public Recompence modifyRecompence(@RequestBody Recompence Recompence) {
		return RecompenceService.updateRecompence(Recompence);
	}
	@GetMapping("/nb/{id}")
	@ResponseBody
	public int nbreRecompse(@PathVariable("id")Long id) {
		return RecompenceService.nbreRecompse(id);
	}
	@GetMapping("/most")
	@ResponseBody
	public User mostRecomponsedUser() {
	return RecompenceService.mostRecomponsedUser();
	}
}
