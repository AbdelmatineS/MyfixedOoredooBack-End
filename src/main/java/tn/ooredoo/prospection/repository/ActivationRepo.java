package tn.ooredoo.prospection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.ooredoo.prospection.entity.Activation;

public interface ActivationRepo extends JpaRepository<Activation,Integer> {

	Activation findTopByOrderByIdDesc();
	List<Activation> findByUsercId(Long userId);
}
