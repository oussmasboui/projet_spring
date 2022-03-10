package tn.esprit.spring.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.BadageQuizz;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.Quiz;
import tn.esprit.spring.entities.Response;
import tn.esprit.spring.service.IRespons;
import tn.esprit.spring.service.ResponseService;

import java.util.List;
import java.util.Set;

@RestController
@Api(tags = "Response")
@RequestMapping("/response")

public class ResponseControlleur {
    @Autowired
    ResponseService rs;
    @PostMapping("/add/{idp}/{idq}/{answer}")
    @ResponseBody
    public Response addResponse(@PathVariable("idp") Long idP,@PathVariable("idq") Long idQ,@PathVariable("answer") Long response) {
        return rs.addResponse(idP, idQ, response);
    }
    @GetMapping("/maxscore/{id}")
    @ResponseBody
    public int getmaxscore(@PathVariable("id") Long id){
        return rs.getmaxscore(id);
    }
    @GetMapping("/BestProfil/{id}")
    @ResponseBody
    public Set<User> getBestuser(@PathVariable("id") Long id) {
        return rs.getBestuser(id);
    }
    @GetMapping("/moyenne/{id}")
    @ResponseBody
    float  getMoyenne(@PathVariable("id") Long id){
        return rs.getuserMoyenne(id);
    }
    @GetMapping("/correcte/{id}")
    @ResponseBody
    public int nbrCorrecte(@PathVariable("id")Long id) {
        return rs.nbrCorrecte(id);
    }
    @GetMapping("/tops")
    @ResponseBody
    public List<User> top(){
        return rs.top();
    }
    @GetMapping("/badge/{id}")
    @ResponseBody
    public BadageQuizz getBadage( @PathVariable("id") Long id){
       return rs.getBadage(id);
    }
}
