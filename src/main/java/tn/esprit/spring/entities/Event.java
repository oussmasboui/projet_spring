package tn.esprit.spring.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
//@ToString
@RequiredArgsConstructor
@Entity

public class Event implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long  idevent;
	private String eventtype;
	private int numInv;
	private int minInv;
	private String entrepriseName;
	private String partenaire;
	private String lieu;
	private Date datedebut;
	private Date datefin;
	private String subject;
	private String summary;
	@ManyToMany
	@JsonIgnore
	private Set<User> users;
	
}
