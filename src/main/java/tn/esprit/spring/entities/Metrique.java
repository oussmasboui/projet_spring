package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@RequiredArgsConstructor

@Entity
public class Metrique implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long idMetrique;
	private String productivity;
	private String workingHours;
	private String communication;
	private String commitment;
	
	@OneToOne
	private User user;
}
