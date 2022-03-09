package tn.esprit.spring.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Question{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qusetionId;
	private String content;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private int answer;




}
