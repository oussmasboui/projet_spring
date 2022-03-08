package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Difficuilte;
import tn.esprit.spring.entities.Metrique;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.MetriqueRepository;
import tn.esprit.spring.repository.UserRepository;


@Service
public class MetriqueServiceImpl implements MetriqueService {
	 @Autowired 
	 MetriqueRepository Metriquerepository;
	@Autowired
	UserRepository us;
	@Override
	public Metrique addMetrique(Metrique m,Long id) {
		User user=us.findById(id).orElse(null);
		m.setUser(user);
		return Metriquerepository.save(m);

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

	@Override
	public int nbreMissionFinit(Long id, Difficuilte difficuilte) {
		if(Metriquerepository.nbreMissionFinit(id, difficuilte)==null)
			return 0;
		return Metriquerepository.nbreMissionFinit(id, difficuilte);
	}

	@Override
	public int nbreHeureTravail(Long id) {
		return Metriquerepository.nbreHeureTravail(id);
	}

	@Override
	public float UserRate(Long id) {
		return (float)(Metriquerepository.nbreMissionFinit(id,Difficuilte.easy)+
				Metriquerepository.nbreMissionFinit(id,Difficuilte.intermediate)*2
		    +Metriquerepository.nbreMissionFinit(id,Difficuilte.hard)*3)/Metriquerepository.nbreHeureTravail(id);
	}
    public int sup(User u,User v){
		if(this.UserRate(u.getIdUser())>this.UserRate(v.getIdUser()))
			return 1;
		if(this.UserRate(u.getIdUser())<this.UserRate(v.getIdUser()))
			return -1;
		return 0;
	}
	@Override
	public List<User> orderUser() {
		List<User> users= new ArrayList<>( Metriquerepository.Muser());
		return users.stream().sorted((v,u)-> this.sup(u,v)).collect(Collectors.toList());
	}

}
