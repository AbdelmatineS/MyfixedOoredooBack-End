package tn.ooredoo.prospection.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.ooredoo.prospection.entity.ERole;
import tn.ooredoo.prospection.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	  Optional<Role> findByName(ERole name);

}
