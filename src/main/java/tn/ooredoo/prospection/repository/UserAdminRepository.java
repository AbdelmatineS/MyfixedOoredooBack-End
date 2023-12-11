package tn.ooredoo.prospection.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.ooredoo.prospection.entity.UserAdmin;

@Repository
public interface UserAdminRepository extends JpaRepository<UserAdmin, Long>{

	
	  Optional<UserAdmin> findByUsername(String username);

	  Boolean existsByUsername(String username);

	  Boolean existsByEmail(String email);
	  
}
