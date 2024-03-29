package tn.ooredoo.prospection.entity;

import javax.persistence.Column;
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


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Raccordement {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String offres ;

    private String debit ;
    @Column(name = "mondatTT", columnDefinition = "LONGBLOB")
    private byte[] mondatTT;
    @ManyToOne
    private UserConseiller userc;
    
	@OneToOne
	@JsonIgnore
	private Activation activation;
}
