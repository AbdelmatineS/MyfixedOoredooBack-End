package tn.ooredoo.prospection.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.ooredoo.prospection.controller.CustomBooleanDeserializer;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FastBox {
	
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    private String categorie ;
	    private String numeroserie;
	    private String debit;
	    private String abonnement;
	    private String prix;
	    private String msisdn;
	    private String numeroTT;

	    @Column(name = "contrat_image", columnDefinition = "LONGBLOB")
	    private byte[] contratImage;

	    @Column(name = "preuve_image", columnDefinition = "LONGBLOB")
	    private byte[] preuveImage;

	    @Column(name = "demande_image", columnDefinition = "LONGBLOB")
	    private byte[] demandeImage;



	    @JsonProperty("clientPossedeNumero")
	    @JsonDeserialize(using = CustomBooleanDeserializer.class)
	    private boolean clientPossedeNumero;



	    @Column(name = "signature_image", columnDefinition = "BLOB")
	    private byte[] signatureImage;

}
