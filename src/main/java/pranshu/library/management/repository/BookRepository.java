package pranshu.library.management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pranshu.library.management.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

	@Query("SELECT t FROM Book t WHERE "+
			":filter IS NULL OR t.author LIKE %:filter%  AND "+
			":filter IS NULL OR t.title LIKE %:filter%")
	Page<Book> findAllBookByFilter(@Param("filter") String filter, Pageable pageable);

}
