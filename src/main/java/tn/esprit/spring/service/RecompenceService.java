package tn.esprit.spring.service;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.entities.Recompence;


public interface RecompenceService {
	public Recompence addRecompence(Recompence r);
	public List<Recompence> retrieveAllRecompence();
	public Recompence retrieveRecompence(Long idRecompence);
	public void deleteRecompence(Long idRecompence);
	public Recompence updateRecompence(Recompence r);
}
