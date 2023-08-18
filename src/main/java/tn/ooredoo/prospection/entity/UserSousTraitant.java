package tn.ooredoo.prospection.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class UserSousTraitant extends User{
	
	/**
	 * 
	 */
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  
	  private String Zone;
	
	  private String Etat;
	  
	  @OneToMany (mappedBy = "userst")
	  private Set<DemandeInter> demandes;
	  

}
