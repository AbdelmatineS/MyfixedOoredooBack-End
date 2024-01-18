package tn.ooredoo.prospection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.ooredoo.prospection.entity.Reglement;

public interface ReglementRepo extends JpaRepository<Reglement,Long> {
	
	List<Reglement> findByUsercId(Long userId);
}
