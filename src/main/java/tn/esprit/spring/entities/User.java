package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.lang.StringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class User implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	
	private long idUser;
	private int phoneNbr;
	private String email;
	private String password;
	private String name;
	private String description;
	private String image;
	private String sanitary_pass;
	private String profession;
	private String domain;
	private String followersNbr;
	private String adress;
	@Temporal(TemporalType.DATE)
	private Date dateNaiss;
	@Enumerated(EnumType.STRING)
	private Role role;
	private Boolean blocked;
	private int scoreEvents;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Post> posts ;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Claim> claims ;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Message> messages ;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="")
	private Set<Opportunity> opportunity;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Travel> travels;
	
	@JsonIgnore
	@ManyToMany(cascade=CascadeType.ALL)
	private Set<Invitation> invitations;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private Set<Recompence>recompence ;
	
	@JsonIgnore
	@OneToOne(mappedBy="user")
	private Metrique metrique;
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="users")
	private Set<Event> event;
	
	@JsonIgnore
	@ManyToMany
	private Set<User> Friends ; 

}
