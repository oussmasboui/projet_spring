package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Metrique;
import tn.esprit.spring.repository.MetriqueRepository;


@Service
public class MetriqueServiceImpl implements MetriqueService {
	 @Autowired 
	 MetriqueRepository Metriquerepository;
	@Override
	public Metrique addMetrique(Metrique m) {
		Metriquerepository.save(m);
		return m;
	}

	@Override
	public List<Metrique> retrieveAllMetrique() {
		return (List<Metrique>)Metriquerepository.findAll();
	}

	@Override
	public Metrique retrieveMetrique(Long idMetrique) {
		return Metriquerepository.findById(idMetrique).get();
	}

	@Override
	public void deleteMetrique(Long idMetrique) {
		Metriquerepository.deleteById(idMetrique);
		
	}

	@Override
	public Metrique updateMetrique(Metrique m) {
		Metriquerepository.save(m);
		return m;
	}

}
