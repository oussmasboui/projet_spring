package tn.esprit.spring.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity

public class Event implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int  idevent;
	private String eventtype;
	private int numinvité;
	private String entrepriseName;
	private String partenaire;
	private String lieu;
	private Date dateevent;
	@ManyToMany
	private Set<User> user;
	

}
