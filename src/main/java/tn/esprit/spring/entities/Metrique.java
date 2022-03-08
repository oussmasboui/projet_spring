package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


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
public class Metrique{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idMetrique;
	private String misson;
	private int workingHours;
	@Enumerated(EnumType.STRING)
	private Difficuilte difficuilte;
	@Enumerated(EnumType.STRING)
	private Etat etat;
	@JsonIgnore
	@ManyToOne
	private User user;
}
