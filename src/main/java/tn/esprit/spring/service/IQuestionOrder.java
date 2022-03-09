package tn.esprit.spring.service;

import tn.esprit.spring.entities.Qpostion;

public interface IQuestionOrder {
    public Qpostion addQuestionOrder(Long idQuizz, Long idQuestion, int order);
    public void DeleteQuestionOrder(Long idQuestion,Long idQuiz);
    public Integer getOrder(Long idQuizz,Long idQuestion);
}
