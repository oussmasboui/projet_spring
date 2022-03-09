package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Message implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)

	private long idMessage;
	private String message;
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne
    private User user;
}
