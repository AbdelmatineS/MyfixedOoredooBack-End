package tn.ooredoo.prospection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.ooredoo.prospection.entity.FastBox;


public interface FastBoxRepo extends JpaRepository<FastBox,Integer> {
    boolean existsByNumeroTT(String numeroTT);



}
