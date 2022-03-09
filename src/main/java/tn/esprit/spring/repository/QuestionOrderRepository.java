package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.Qpostion;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.QuestionOrderID;

import java.util.List;


@Repository
public interface QuestionOrderRepository extends JpaRepository<Qpostion, QuestionOrderID> {
    @Query("select Q.questionOrderId.question from Qpostion Q where Q.questionOrderId.quiz.IdQuiz= :id   ")
    public List<Question> getQuestionByquiz(@Param("id") Long id);
    @Query("select Q.postion from Qpostion Q where Q.questionOrderId.quiz.IdQuiz= :idquiz and Q.questionOrderId.question.qusetionId= :idq   ")
    public int getPostion (@Param("idquiz") Long id, @Param("idq") Long idq);

}