package tn.ooredoo.prospection.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
    
	private String category;
		
	private String Abonnement;
	
	//private Long msisdn;
	
	//private Long msisdn;
	
	//private String fullName;
	
	//private String adresse;
	
    //private String cgps;

	
	//private Long contact;
	
	private LocalDateTime datePlanif;
	
	@ManyToOne
    @JoinColumn(name = "user_st_id")
	@JsonIgnore
	private UserSousTraitant userst;
	
	@ManyToOne
    @JoinColumn(name = "user_admin_id")
	@JsonIgnore
	private UserAdmin usera;
	
//	@OneToOne(mappedBy = "demande_prosp")
//	//@JsonIgnore
//	private Prospection prospection;
//	
//	@OneToOne(mappedBy = "demande_res", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	//@JsonIgnore
//	private Reservation reservation;
	
	@OneToOne(mappedBy = "demande_act", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@JsonIgnore
	private Activation activation;
	
	@Column(name="status")
	private String status;
	
	@OneToMany(mappedBy = "demande")
	private Set<FicheInter> fiches;
	
	
	
}
