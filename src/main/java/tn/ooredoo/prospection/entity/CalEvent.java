package tn.ooredoo.prospection.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private boolean allDay;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSousTraitant userst;

    @ManyToOne
    @JoinColumn(name = "demande_id")
    private DemandeInter demande;
}
