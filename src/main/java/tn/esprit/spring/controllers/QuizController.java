package tn.esprit.spring.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Quiz;
import tn.esprit.spring.service.QuestionOrderService;
import tn.esprit.spring.service.QuizService;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@Api(tags = "Quiz")
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService qs;
    @Autowired
    QuestionOrderService qos;

    @PostMapping("/add")
    @ResponseBody()
    public Quiz addQuiz() {
        return qs.addQuiz();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void removeQuiz(@PathVariable("id") Long id) {
      qs.removeQuiz(id);
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public Quiz getQuiz(@PathVariable("id") Long id) {
        return qs.getQuiz(id);
    }
    @GetMapping("/get-all")
    @ResponseBody
    public List<Quiz> getQuizs() {
        return qs.getQuizs();
    }
    @GetMapping("/{idq}/order/{idqe}")
    @ResponseBody
    public Integer getOrder(@PathVariable("idq") Long idQuizz,@PathVariable("idqe") Long idQuestion) {
        return qos.getOrder(idQuizz,idQuestion);
    }
    @GetMapping("/questions/{id}")
    @ResponseBody
    public Map<Integer, Question> getQuestion(@PathVariable("id") Long id) {

        return qs.getQuestion(id) ;
    }

}
