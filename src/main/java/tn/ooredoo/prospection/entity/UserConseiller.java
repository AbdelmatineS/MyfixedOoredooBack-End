package tn.ooredoo.prospection.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class UserConseiller extends User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String NomB;
	
	@OneToMany(mappedBy = "userc")
	private Set<Reservation> reservations;
	
	@OneToMany(mappedBy = "userc")
	private Set<Prospection> prospections;
	
	@OneToMany(mappedBy = "userc")
	private Set<Activation> activations;
	
	@OneToMany(mappedBy = "userc")
	private Set<FastBox> ftasboxs;
	
	@OneToMany(mappedBy = "userc")
	private Set<FixeJdid> fixejdids;
	
	@OneToMany(mappedBy = "userc")
	private Set<Maps> maps;
	
	@OneToMany(mappedBy = "userc")
	private Set<FlashBox> flashboxs;
	
	@OneToMany(mappedBy = "userc")
	private Set<Raccordement> raccordements;
	
	@OneToMany(mappedBy = "userc")
	private Set<Reglement> reglements;
	
	
	  public UserConseiller(Long id,String fullName, String username, String email, String password, Long numTel, String zone, String nomB) {
		  super(id,fullName,username,email,password,numTel,zone);

		    this.NomB = nomB;
		  }
	
	  public UserConseiller(String fullName, String username, String email, String password, Long numTel, String zone, String nomB) {
		  super(fullName,username,email,password,numTel,zone);

		    this.NomB = nomB;
		  }
}
