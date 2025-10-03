package pranshu.library.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pranshu.library.management.model.BookCategory;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory,Long> {

}
