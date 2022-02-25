package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
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
public class Invitation implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idInvitation;
	private boolean state;
	@JsonIgnore
	@ManyToOne
	private User user;
	
	//aicha
}