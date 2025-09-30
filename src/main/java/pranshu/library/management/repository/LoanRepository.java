package pranshu.library.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pranshu.library.management.model.Loans;
import pranshu.library.management.model.Loans.Status;

@Repository
public interface LoanRepository extends JpaRepository<Loans,Long> {

	@Query("SELECT COUNT(t) FROM Loans t WHERE "+
			"t.user.id = :id")
	long findAllLoansByUserId(@Param("id")long id);

	@Query("SELECT t FROM Loans t WHERE "+
			"(:status IS NULL OR t.status = :status) AND "+
			"(:search IS NULL OR t.user.name LIKE %:search% OR "+
			":search IS NULL OR t.book.title LIKE %:search%)")
	List<Loans> findFilteredLoan(@Param("status") Status status, @Param("search") String search);

}
