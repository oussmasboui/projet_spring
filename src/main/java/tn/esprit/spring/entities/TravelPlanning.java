package tn.esprit.spring.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class TravelPlanning {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long idPlanning;
	private String schedule;
	private String description;
	private String day;
	

	@ManyToOne
	private Travel travel;
	
}
