package tn.ooredoo.prospection.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.ooredoo.prospection.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	  Optional<User> findByUsername(String username);

	  Boolean existsByUsername(String username);

	  Boolean existsByEmail(String email);
	  
	  List<User> findByFullNameContainingIgnoreCase(String query);
	  
	  List<User> findByZoneContainingIgnoreCase(String query);


}
