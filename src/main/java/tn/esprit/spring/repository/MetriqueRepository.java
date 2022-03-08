package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Difficuilte;
import tn.esprit.spring.entities.Metrique;
import tn.esprit.spring.entities.User;

import java.util.Set;

@Repository
public interface MetriqueRepository extends CrudRepository<Metrique, Long>{
    @Query("select sum(M.workingHours) from Metrique M where M.user.idUser = :id and M.etat='Accomplished'")
    public Integer nbreHeureTravail(@Param("id") Long id);
    @Query("select count(M) from Metrique M where M.difficuilte=:diff and M.user.idUser = :id and M.etat='Accomplished' ")
    public Integer nbreMissionFinit(@Param("id")Long id, @Param("diff")Difficuilte diff);
    @Query("select M.user from Metrique M ")
    public Set<User> Muser();
}



