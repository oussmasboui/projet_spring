package tn.esprit.spring.service;

import tn.esprit.spring.entities.BadageQuizz;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.entities.Response;

import java.util.List;
import java.util.Set;

public interface IRespons {
    public Response addResponse(Long idP,Long idQ,Long reponse);
    public Set<User> getBestuser(Long id);
    public float  getuserMoyenne( Long id);
    public int nbrCorrecte(Long id);
    public List<User> top();
    public BadageQuizz getBadage(Long id);
}
