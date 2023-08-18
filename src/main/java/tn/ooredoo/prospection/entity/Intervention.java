package tn.ooredoo.prospection.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Intervention {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Date date ;
    private String type ;
    private String Categories;
    private String abonnement;
    private String msisdn;
    private String nom ;
    private String prenom;
    private String adresse;
    private String cgps;
    private String contact;
    @JsonIgnore
    private String planification;

}
