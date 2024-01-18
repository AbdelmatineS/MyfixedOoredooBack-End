package tn.ooredoo.prospection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.ooredoo.prospection.entity.FicheInter;

@Repository
public interface FicheInterRepository extends JpaRepository<FicheInter, Long>{
	
	
	FicheInter findTopByOrderByIdDesc();
    List<FicheInter> findByUserstId(Long userId);
    List<FicheInter> findByDemandeId(Long demandeId);

}
