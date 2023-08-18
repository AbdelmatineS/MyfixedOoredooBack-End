package tn.ooredoo.prospection.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.ooredoo.prospection.entity.FlashBox;

import java.util.List;

@Repository
public interface FlashBoxRepo extends JpaRepository<FlashBox,Long> {
    FlashBox findByMsisdn(String msisdn);
    List<FlashBox> findByMsisdnStartsWith(String prefix);




}
