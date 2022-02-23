package tn.esprit.spring.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Metrique;
import tn.esprit.spring.entities.Recompence;
import tn.esprit.spring.repository.MetriqueRepository;
import tn.esprit.spring.repository.RecompenceRepository;
@Service
public class RecompenceServiceImpl implements RecompenceService {
	 @Autowired 
	 RecompenceRepository Recompencerepository;
	@Override
	public Recompence addRecompence(Recompence r) {
		Recompencerepository.save(r);
		return r;
	}

	@Override
	public List<Recompence> retrieveAllRecompence() {
		return (List<Recompence>)Recompencerepository.findAll();
		
	}

	@Override
	public Recompence retrieveRecompence(Long idRecompence) {
	
		return Recompencerepository.findById(idRecompence).get();
	}

	@Override
	public void deleteRecompence(Long idRecompence) {
		Recompencerepository.deleteById(idRecompence);
		
	}

	@Override
	public Recompence updateRecompence(Recompence r) {
		Recompencerepository.save(r);
		return r;
	}

}
