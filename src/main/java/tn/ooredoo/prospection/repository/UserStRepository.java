package tn.ooredoo.prospection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.ooredoo.prospection.entity.DemandeInter;

@Repository
public interface UserStRepository extends JpaRepository<DemandeInter, Long>{

}
