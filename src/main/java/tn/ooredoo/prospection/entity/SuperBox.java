package tn.ooredoo.prospection.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuperBox {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String choix ;
    private String iccid;
    private String numeroserie;
    private String zonerecherche;
    private String msisdn;

    @Column(name = "signature_image", columnDefinition = "BLOB")
    private byte[] signatureImage;

}
