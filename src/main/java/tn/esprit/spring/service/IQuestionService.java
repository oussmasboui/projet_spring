package tn.esprit.spring.service;

import tn.esprit.spring.entities.Question;

import java.util.List;

public interface IQuestionService {
    public Question addQuestion(Question Q);
    public void removeQuestion(Long id);
    public Question updateQuestion(Question Q);
    public Question getQuestion(Long id);
    public List<Question> getQuestions();
}
