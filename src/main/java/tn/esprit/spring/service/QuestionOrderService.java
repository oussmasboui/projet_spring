package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Qpostion;
import tn.esprit.spring.entities.QuestionOrderID;
import tn.esprit.spring.entities.Quiz;
import tn.esprit.spring.repository.*;

@Service
public class QuestionOrderService implements IQuestionOrder{
        @Autowired
        QuestionOrderRepository qor;
        @Autowired
        QuestionRepository qr;
        @Autowired
        QuizRepository quizRepository;

        @Override
        public Qpostion addQuestionOrder(Long idQuizz, Long idQuestion, int order) {
            Qpostion qo=new Qpostion();
            Question question=qr.findById(idQuestion).orElse(null);
            Quiz quiz=quizRepository.findById(idQuizz).orElse(null);
            QuestionOrderID questionOrderID=new QuestionOrderID();
            questionOrderID.setQuestion(question);
            questionOrderID.setQuiz(quiz);
            qo.setPostion(order);
            qo.setQuestionOrderId(questionOrderID);
            return qor.save(qo);
        }

        @Override
        public void DeleteQuestionOrder(Long idQuestion,Long idQuiz) {
            QuestionOrderID qoi=new QuestionOrderID();
            Question question=qr.findById(idQuestion).orElse(null);
            Quiz quiz=quizRepository.findById(idQuiz).orElse(null);
            qoi.setQuestion(question);
            qoi.setQuiz(quiz);
            qor.deleteById(qoi);
        }

        @Override
        public Integer getOrder(Long idQuizz, Long idQuestion) {

            return qor.getPostion(idQuizz,idQuestion);
        }

}

