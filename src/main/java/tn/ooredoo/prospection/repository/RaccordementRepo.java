package tn.ooredoo.prospection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.ooredoo.prospection.entity.Raccordement;

public interface RaccordementRepo extends JpaRepository<Raccordement,Integer> {

	List<Raccordement> findByUsercId(Long userId);
}
