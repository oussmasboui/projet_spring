package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.QuestionOrderID;
import tn.esprit.spring.entities.Quiz;
import tn.esprit.spring.repository.*;

import java.util.*;

@Service
public class QuizService implements IQuizService{

    @Autowired
    QuizRepository qr;
    @Autowired
    QuestionRepository qer;
    @Autowired
    QuestionOrderRepository qor;
    @Autowired
    QuestionOrderService qos;
    @Override
    public Quiz addQuiz() {
        Quiz q=new Quiz();
        q=qr.save(q);
        List<Question> questions= (List<Question>) qer.findAll();
        Random rand = new Random();
        List<Question>choices=new ArrayList<>();
        int j=1;
        long codeReponse=0;
        while (choices.size()!=10){
            int i=rand.nextInt(questions.size());
            if(!choices.contains(questions.get(i)) ){
                choices.add(questions.get(i));
                qos.addQuestionOrder(q.getIdQuiz(),questions.get(i).getQusetionId(),j);
                codeReponse+=questions.get(i).getAnswer()*Math.pow(10,j-1);
                j++;

            }
        }
       q.setCodeReponse(codeReponse);
        return qr.save(q);


    }

    @Override
    public void removeQuiz(Long id) {
        qr.deleteById(id);
    }

    @Override
    public Quiz getQuiz(Long id) {
        return qr.findById(id).orElse(null);
    }

    @Override
    public List<Quiz> getQuizs() {
        return (List<Quiz>) qr.findAll();
    }

    @Override
    public Map<Integer, Question> getQuestion(Long id) {
        List<Question> questions=qor.getQuestionByquiz(id);
        Map map=new HashMap();
         questions.forEach(q->map.put(qos.getOrder(id,q.getQusetionId()),q));
        return map ;
    }
}
