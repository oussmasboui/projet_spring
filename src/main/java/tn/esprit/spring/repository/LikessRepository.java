package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Likess;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.User;

public interface LikessRepository extends JpaRepository<Likess, Long>{
	Likess findByPostAndUser(Post post, User user);
	
	@Query("SELECT l.user.idUser, COUNT(l.user) FROM Likess AS l WHERE date >= :startDate AND date <= :endDate GROUP BY l.user ORDER BY COUNT(l.user) DESC")
	List<Object[]> countTotalLikesByMonth(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
