package tn.esprit.spring.service;

import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Quiz;

import java.util.List;
import java.util.Map;

public interface IQuizService {
     Quiz addQuiz();
     void removeQuiz(Long id);
     Quiz getQuiz(Long id);
     List<Quiz> getQuizs();
     Map<Integer, Question> getQuestion(Long id);
}
