package tn.ooredoo.prospection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.ooredoo.prospection.entity.DemandeInter;

@Repository
public interface DemandeInterRepository extends JpaRepository<DemandeInter, Long> {

	DemandeInter findTopByOrderByIdDesc();

}
