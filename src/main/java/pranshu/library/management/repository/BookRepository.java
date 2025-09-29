package pranshu.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pranshu.library.management.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

}
