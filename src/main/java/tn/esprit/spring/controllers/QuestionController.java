package tn.esprit.spring.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.service.QuestionService;

import java.util.List;

@RestController
@Api(tags = "Question")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService qs;
   @PostMapping("/add")
   @ResponseBody
    public Question addQuestion(@RequestBody Question q) {
        return qs.addQuestion(q);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void removeQuestion(@PathVariable("id") Long id) {
        qs.removeQuestion(id);
    }

    @PutMapping("/update")
    @ResponseBody
    public Question updateQuestion(@RequestBody Question q) {
        return qs.updateQuestion(q);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Question getQuestion(@PathVariable("id") Long id) {
        return qs.getQuestion(id);
    }

    @GetMapping("/get-all")
    @ResponseBody
    public List<Question> getQuestions() {
        return (List<Question>) qs.getQuestions();
    }
}
