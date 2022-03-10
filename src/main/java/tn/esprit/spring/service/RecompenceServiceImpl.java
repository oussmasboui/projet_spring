package tn.esprit.spring.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Metrique;
import tn.esprit.spring.entities.Recompence;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.MetriqueRepository;
import tn.esprit.spring.repository.RecompenceRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class RecompenceServiceImpl implements RecompenceService {
	 @Autowired 
	 RecompenceRepository Recompencerepository;
	 @Autowired
	UserRepository us;
	@Override
	public Recompence addRecompence(Recompence r,Long id) {
		User user=us.findById(id).orElse(null);
		r.setUser(user);
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

	@Override
	public int nbreRecompse(Long id) {
		if(Recompencerepository.nbreRecompse(id) == null) {
			return 0;
		}
		return Recompencerepository.nbreRecompse(id);
	}

	@Override
	public User mostRecomponsedUser() {
		Set<User> users=  Recompencerepository.Ruser();
		User user=users.stream().max((u,v)->this.nbreRecompse(u.getIdUser())-this.nbreRecompse(v.getIdUser())).orElse(null);
		return user;
	}

}
