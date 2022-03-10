package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

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
	/**
	 * 
	 **/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idInvitation;
	private boolean state;
	
	@Temporal(TemporalType.DATE)
	private Date dateinvitation;
	private String location;
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL,mappedBy="invitations")
	private Set<User> users;
    
	//aicha
}