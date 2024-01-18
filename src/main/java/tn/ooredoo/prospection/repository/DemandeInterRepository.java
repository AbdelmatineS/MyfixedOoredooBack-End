package tn.ooredoo.prospection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.ooredoo.prospection.entity.DemandeInter;

@Repository
public interface DemandeInterRepository extends JpaRepository<DemandeInter, Long> {

	DemandeInter findTopByOrderByIdDesc();
    List<DemandeInter> findByUserstId(Long userId);
    List<DemandeInter> findByStatus(String status);

}
