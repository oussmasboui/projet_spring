package tn.esprit.spring.service;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.spring.entities.Recompence;
import tn.esprit.spring.entities.User;


public interface RecompenceService {
	public Recompence addRecompence(Recompence r,Long id);
	public List<Recompence> retrieveAllRecompence();
	public Recompence retrieveRecompence(Long idRecompence);
	public void deleteRecompence(Long idRecompence);
	public Recompence updateRecompence(Recompence r);
	public int nbreRecompse(Long id);
	public User mostRecomponsedUser();
}
