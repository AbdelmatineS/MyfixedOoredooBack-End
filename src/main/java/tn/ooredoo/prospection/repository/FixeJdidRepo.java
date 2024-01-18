package tn.ooredoo.prospection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.ooredoo.prospection.entity.Activation;
import tn.ooredoo.prospection.entity.FixeJdid;

import java.util.List;

@Repository
public interface FixeJdidRepo extends JpaRepository<FixeJdid,Integer> {
	FixeJdid findTopByOrderByIdDesc();
    FixeJdid findByMsisdn(String msisdn);
    List<FixeJdid> findByMsisdnStartsWith(String prefix);
    List<FixeJdid> findByActivation(Activation activation);
    List<FixeJdid> findByUserc(Activation activation);

}

