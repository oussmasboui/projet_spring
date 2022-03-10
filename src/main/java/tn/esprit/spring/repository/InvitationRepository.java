package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Invitation;


@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long>{
List<Invitation>findBystate(boolean state) ;

@Query(value="SELECT * FROM invitation where invitation.state=? 1", nativeQuery = true)
public List<Invitation> getBystate(Boolean state);


@Query("select count(id_invitation) from Invitation i where month(i.dateinvitation) = ?1")
public Float getByMonth(int month);

}
