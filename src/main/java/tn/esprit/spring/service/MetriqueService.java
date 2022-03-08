package tn.esprit.spring.service;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Difficuilte;
import tn.esprit.spring.entities.Metrique;
import tn.esprit.spring.entities.User;

public interface MetriqueService {
	public Metrique addMetrique(Metrique m,Long id);
	public List<Metrique> retrieveAllMetrique();
	public Metrique retrieveMetrique(Long idMetrique);
	public void deleteMetrique(Long idMetrique);
	public Metrique updateMetrique(Metrique m);
	public int nbreMissionFinit(Long id, Difficuilte dif);
	public int nbreHeureTravail(Long id);
	public float UserRate(Long id);//(easy+2intermdaire+3*difficle)/nb herue
	public List<User> orderUser();


	
}
