package tn.ooredoo.prospection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.ooredoo.prospection.entity.NotificationToken;

public interface NotificationTokenRepository extends JpaRepository<NotificationToken, Long> {

}
