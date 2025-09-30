package pranshu.library.management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pranshu.library.management.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	@Query("SELECT t FROM User t WHERE "+
			":filter IS NULL OR t.name LIKE %:filter% OR "+
			":filter IS NULL OR t.email LIKE %:filter% OR "+
			":filter IS NULL OR t.phone LIKE %:filter%")
	Page<User> findAllUserByFilter(@Param("filter") String filter, Pageable pageable);

}
