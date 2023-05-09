package tn.ooredoo.prospection.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.ooredoo.prospection.entity.Prospection;


@Repository
public interface ProspectionRepository extends JpaRepository<Prospection, Long>{

	List<Prospection> findByOrderByIdDesc();
}
