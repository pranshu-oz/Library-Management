package pranshu.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pranshu.library.management.model.Loans;

@Repository
public interface LoanRepository extends JpaRepository<Loans,Long> {

}
