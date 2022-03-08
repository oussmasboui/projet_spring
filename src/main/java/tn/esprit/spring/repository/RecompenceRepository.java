package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Recompence;
import tn.esprit.spring.entities.User;

import java.util.Set;

@Repository
public interface RecompenceRepository extends CrudRepository<Recompence, Long>{
    @Query("select count(R) from Recompence R where R.user.idUser = :id")
    public Integer nbreRecompse(@Param("id") Long id);
    @Query("select R.user from Recompence R ")
    public Set<User> Ruser();

}
