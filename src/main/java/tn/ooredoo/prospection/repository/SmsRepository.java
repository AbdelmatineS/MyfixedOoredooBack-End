package tn.ooredoo.prospection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.ooredoo.prospection.entity.SMSrequest;

public interface SmsRepository extends JpaRepository<SMSrequest, Long> {
}

