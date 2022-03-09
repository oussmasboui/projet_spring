package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.entities.Response;

import java.util.List;
import java.util.Set;

@Repository
public interface ResponseRepository extends CrudRepository<Response, Long> {
    @Query("select R.user from Response R where R.quiz.IdQuiz =:id and R.score=:max")
    Set<User> getBestUser(@Param("id") Long id, @Param("max") int max);
    @Query("select max(R.score) from Response R where R.quiz.IdQuiz =:id ")
    int  getmaxScore(@Param("id") Long id);
    @Query("select sum(R.score)/count(R) from Response R where R.user.idUser =:id")
    float  getMoyenne(@Param("id") Long id);
    @Query("select count(R) from Response R where R.user.idUser =:id and R.score=100")
    public Integer nbrCorrecte(@Param("id")Long id);
    @Query("select R.user from Response R ")
    Set<User> geUser();
}
