
package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.BadageQuizz;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.entities.Quiz;
import tn.esprit.spring.entities.Response;
import tn.esprit.spring.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ResponseService implements IRespons {
    @Autowired
    ResponseRepository rr;
    @Autowired
    UserRepository pr;
    @Autowired
    QuizRepository qr;
    @Override
    public Response addResponse(Long idP,Long idQ,Long response) {
        Response r=new Response();
        Quiz q=qr.findById(idQ).orElse(null);
        User p=pr.findById(idP).orElse(null);
        r.setUser(p);
        r.setResponseCode(response);
        r.setQuiz(q);
        int score=0;
        long x=q.getCodeReponse();
        while(x>10 ){
            if(x%10==response%10)
                score+=10;
            x=x/10;
            response=response/10;
        }
        if(x%10 ==response%10)
            score+=10;
        r.setScore( score);

        return rr.save(r);

    }
    public int getmaxscore(Long id){
        return rr.getmaxScore(id);
    }
    @Override
    public Set<User> getBestuser(Long idq) {
        return rr.getBestUser(idq,rr.getmaxScore(idq));
    }
    public float  getuserMoyenne( Long id){
        return rr.getMoyenne(id);
    }

    @Override
    public int nbrCorrecte(Long id) {
        return rr.nbrCorrecte(id);
    }

    @Override
    public List<User> top() {
       Set<User> users=  rr.geUser();
       List<User> list =new ArrayList(users);
       list=users.stream().sorted((u,v)->rr.nbrCorrecte(v.getIdUser())-rr.nbrCorrecte(u.getIdUser())).collect(Collectors.toList());
       long n= (long) (list.size()*0.1);
       if(n==0) n=1;
        return list.stream().limit(n).collect(Collectors.toList());
    }

    @Override
    public BadageQuizz getBadage(Long id) {
    	User p=pr.findById(id).orElse(null);
        List<User>users=this.top();
        if(p!=null)
        {
            if(users.contains(p))
            {
                p.setBadageQuizz(BadageQuizz.Elite);
                pr.save(p);
                return p.getBadageQuizz();
            }
            if(rr.nbrCorrecte(p.getIdUser())>=10)
            {
                p.setBadageQuizz(BadageQuizz.smart);
                pr.save(p);
                return p.getBadageQuizz();
            }
            if(rr.nbrCorrecte(p.getIdUser())>0)
            {
                p.setBadageQuizz(BadageQuizz.first);
                pr.save(p);
                return p.getBadageQuizz();
            }
        }
        return null;
    }

}
