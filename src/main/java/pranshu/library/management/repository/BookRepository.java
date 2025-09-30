package pranshu.library.management.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pranshu.library.management.model.Book;
import pranshu.library.management.model.Loans.Status;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

	@Query("SELECT t FROM Book t WHERE "+
			":filter IS NULL OR t.author LIKE %:filter%  AND "+
			":filter IS NULL OR t.title LIKE %:filter%")
	Page<Book> findAllBookByFilter(@Param("filter") String filter, Pageable pageable);

	@Query("SELECT COUNT(t) FROM Book t")
	long getTotalBooks();

	@Query("SELECT t.issueDate, SUM(CASE WHEN t.status =:status THEN 1 ELSE 0 END) "+
			"FROM Loans t WHERE "+
			"t.issueDate BETWEEN :startOfWeek AND :endOfWeek "+
			"GROUP BY t.issueDate "+
			"ORDER BY t.issueDate")
	List<Object[]> getCurrentWeekStats(@Param("startOfWeek") LocalDate startOfWeek, @Param("endOfWeek") LocalDate endOfWeek, @Param("status") Status status);

	@Query("SELECT t.title, "+
			"t.availableCopy FROM Book t")
	List<Object[]>getAvailableStats();

}
