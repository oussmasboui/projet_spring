package tn.esprit.spring.repository;

import java.util.Date;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.SubComment;

public interface SubCommentRepository extends JpaRepository<SubComment, Long> {
	@Query("SELECT s.user.idUser, COUNT(s.user) FROM SubComment AS s WHERE date >= :startDate AND date <= :endDate GROUP BY s.user ORDER BY COUNT(s.user) DESC")
	List<Object[]> countTotalSubCommentsByMonth(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
