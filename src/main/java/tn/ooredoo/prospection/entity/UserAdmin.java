package tn.ooredoo.prospection.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserAdmin extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "usera")
	private Set<Reservation> reservations;
	
	@OneToMany(mappedBy = "usera")
	private Set<Activation> activations;
	
	@OneToMany(mappedBy = "usera")
	private Set<Prospection> prospections;
	
	@OneToMany(mappedBy = "usera")
	private Set<DemandeInter> demandes;
	
	  public UserAdmin(Long id, String fullName, String username, String email, String password, Long numTel, String zone) {
		  super(id,fullName,username,email,password,numTel,zone);
		  }
	  
	  public UserAdmin(String fullName, String username, String email, String password, Long numTel, String zone) {
		  super(fullName,username,email,password,numTel,zone);
		  }

}
