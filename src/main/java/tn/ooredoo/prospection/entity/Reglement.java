package tn.ooredoo.prospection.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Reglement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String paiement;
    private Double montant;
    private String rib;
    private String numeroCheque;
    private String codeBanque;
    private String codeAgence;
    private Date dateEcheance;
    private String typeIdentite;
    private String numeroIdentite;
    private String code;

   private String numerocarte;
    private String expirationMonth;
    private String expirationYear;
    private String nomdetenteur;
    private String ccv;
    private String email;
    @ManyToOne
    private UserConseiller userc;
    
	@OneToOne
	@JsonIgnore
	private Activation activation;
}
