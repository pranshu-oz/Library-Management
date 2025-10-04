package pranshu.library.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pranshu.library.management.model.RoleManager;

@Repository
public interface RoleManagerRepository extends JpaRepository<RoleManager,Long>{

	
	Optional<RoleManager> findByUsername(String username);
}
