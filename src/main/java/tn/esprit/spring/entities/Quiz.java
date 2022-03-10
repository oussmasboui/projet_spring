package tn.esprit.spring.entities;



import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Quiz  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdQuiz;
	private long codeReponse;
	@JsonIgnore
	@OneToMany(mappedBy = "quiz")
	Set<Response> responses;


}
