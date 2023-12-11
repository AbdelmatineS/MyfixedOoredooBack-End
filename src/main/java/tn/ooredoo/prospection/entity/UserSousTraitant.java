package tn.ooredoo.prospection.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

public class UserSousTraitant  extends User implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	

	
	private String Etat;
	  
	private String NomSt;
	  
	@OneToMany (mappedBy = "user_st")
	private Set<DemandeInter> demandes;
	  
	public UserSousTraitant(String username, String email, String password) {
		super(username, email, password);
	}
	
	  public UserSousTraitant(Long id,String fullName, String username, String email, String password, Long numTel, String zone,String nomSt) {
		  super(id,fullName, username,email,password,numTel,zone);
		  this.NomSt = nomSt;
		  }
	  
	  public UserSousTraitant(String fullName, String username, String email, String password, Long numTel, String zone,String nomSt) {
		  super(fullName, username,email,password,numTel,zone);
		  this.NomSt = nomSt;
		  }
}
