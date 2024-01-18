package tn.ooredoo.prospection.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
public class FicheInter implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String action;
	
	private String raison;

	@Lob
	private byte[] imagePvInter;
	
	private Long snswap;

	private String commentaire;
	
	@ManyToOne
	@JoinColumn(name = "demande_id")
	@JsonIgnore
	private DemandeInter demande;
	
	@ManyToOne
	@JoinColumn(name = "userst_id")
	@JsonIgnore
	private UserSousTraitant userst;
	
}
