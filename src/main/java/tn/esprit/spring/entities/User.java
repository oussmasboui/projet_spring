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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PUBLIC)
@Table(name = "User")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idUser;
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
	private BadageQuizz bq;
	
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user")
	private Set<Opportunity> opportunity;
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private Set<Travel> travels;
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private Set<Invitation> invitations;
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private Set<Recompence>recompence ;

	@JsonIgnore
	@OneToMany(mappedBy="user")
	private Set<Metrique> metriques;
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private BadageQuizz badageQuizz;
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private Poupilarite poupilarite;
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private ReactiviteBadge reactiviteBadge;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	Set<Response> responses;

}
