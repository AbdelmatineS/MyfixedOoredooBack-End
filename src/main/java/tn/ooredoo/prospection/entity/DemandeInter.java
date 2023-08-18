package tn.ooredoo.prospection.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemandeInter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime dateDemande;
	
	private String nature;
	
	private String type;
	
	private String Abonnement;
	
	private Long msisdn;
	
	private String fullName;
	
	private String adresse;
	
	private Double latitude;
	
	private Double longitude;
	
	private Long contact;
	
	private LocalDateTime datePlanif;
	
	@ManyToOne
	@JsonIgnore
	private UserSousTraitant userst;
	
	
	
}
