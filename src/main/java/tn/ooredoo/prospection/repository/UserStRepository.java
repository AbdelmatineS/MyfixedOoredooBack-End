package tn.ooredoo.prospection.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.ooredoo.prospection.entity.UserSousTraitant;

@Repository
public interface UserStRepository extends JpaRepository<UserSousTraitant, Long>{

	  Optional<UserSousTraitant> findByUsername(String username);

	  Boolean existsByUsername(String username);

	  Boolean existsByEmail(String email);
}
