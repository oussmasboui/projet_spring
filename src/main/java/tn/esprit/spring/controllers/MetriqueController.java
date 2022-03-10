package tn.esprit.spring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import tn.esprit.spring.entities.Difficuilte;
import tn.esprit.spring.entities.Metrique;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.service.MetriqueService;



@RestController
@CrossOrigin(origins = "*")
@Api(tags = "Metrique")
@RequestMapping("/Metrique")

public class MetriqueController {
	@Autowired
	MetriqueService MetriqueService ;
	
	

	@GetMapping("/retrieve-all-Metrique")
	@ResponseBody
	public List<Metrique> getMetrique() {
		return MetriqueService.retrieveAllMetrique();

	}
	
	@GetMapping("/retrieve-Metrique/{Metrique-id}")
	@ResponseBody
	public Metrique retrieveMetrique(@PathVariable("Metrique-id") Long idMetrique) {
		return MetriqueService.retrieveMetrique(idMetrique);
	}

	@PostMapping("/{id}/add-Metrique/")
	@ResponseBody
	public Metrique addProduct(@RequestBody Metrique m,@PathVariable("id") Long id) {
		return MetriqueService.addMetrique(m,id);
	}
	@DeleteMapping("/remove-Metrique/{Metrique-id}")
	@ResponseBody
	public void removeMetrique(@PathVariable("Metrique-id") Long idMetrique) {
		MetriqueService.deleteMetrique(idMetrique);
	}

	@PutMapping("/modify-Metrique")
	@ResponseBody
	public Metrique modifyMetrique(@RequestBody Metrique Metrique) {
		return MetriqueService.updateMetrique(Metrique);
	}
	@GetMapping("/nbMission/{id}/{diff}")
	@ResponseBody
	public int nbreMissionFinit(@PathVariable("id") Long id, @PathVariable("diff") Difficuilte difficuilte) {
		return MetriqueService.nbreMissionFinit(id,difficuilte);
	}

	@GetMapping("/nbHeure/{id}/")
	@ResponseBody
	public int nbreHeureTravail(@PathVariable("id")Long id) {
		return  MetriqueService.nbreHeureTravail(id);
	}

	@GetMapping("/Rate/{id}/")
	@ResponseBody
	public float UserRate(@PathVariable("id") Long id) {
		return  MetriqueService.UserRate(id);
	}
	@GetMapping("/order")
	@ResponseBody

	public List<User> orderUser() {

		return MetriqueService.orderUser();
	}
	
}
