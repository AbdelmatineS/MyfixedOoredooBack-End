package tn.ooredoo.prospection.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    private UserConseiller userc;


}
