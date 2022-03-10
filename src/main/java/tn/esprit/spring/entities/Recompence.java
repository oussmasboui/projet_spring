package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Recompence implements Serializable{
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long idRecompence;
	@Enumerated(EnumType.STRING)
	private TypeRecompence typeRecompence;
	
	@ManyToOne


	@JsonIgnore

    private User user;
	
	
	
	
	
	
	
	
	
	

}
