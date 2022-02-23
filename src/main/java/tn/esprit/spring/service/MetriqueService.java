package tn.esprit.spring.service;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Metrique;

public interface MetriqueService {
	public Metrique addMetrique(Metrique m);
	public List<Metrique> retrieveAllMetrique();
	public Metrique retrieveMetrique(Long idMetrique);
	public void deleteMetrique(Long idMetrique);
	public Metrique updateMetrique(Metrique m);
	
}
