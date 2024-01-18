package tn.ooredoo.prospection.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Activation {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
	//@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dateCreation;
	//@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dateDernièreModification;
	
    @Column(name="Nom")

    private String Nom ;
    @Column(name="Prenom")

    private String Prenom;
    @Column(name="numero_identite")

    private String numeroidentite;
    @Column(name="date_naissance")

    private Date naissance;
    @Column(name="Gouvernorat")

    private String gouvernorat;
    @Column(name="Delegation")

    private String delegation;
    @Column(name="Localite")

    private String localite;
    @Column(name="Adresse")

    private String adresse ;
    @Column(name="code_postale")

    private String codepostale;
    @Column(name="numContact")

    private String numContact;
    @Column(name="Email")

    private String email;
    @Column(name="Nationalite",nullable = true)

    private String nationalite;
    
    
    @ManyToOne
    @JsonIgnore
    private UserConseiller userc;
    
	private Double latitude;
	private Double longitude;
    
	@OneToOne
	@JsonIgnore
	private DemandeInter demande_act;

	@OneToOne(mappedBy = "activation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Reglement reg;
	
	@OneToOne(mappedBy = "activation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@JsonIgnore
	private FixeJdid fix;
	
	@OneToOne(mappedBy = "activation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Raccordement racc;
	
	@ManyToOne
	@JsonIgnore
	private UserAdmin usera;
	
	@Column(name="status")
	private String status;

}
