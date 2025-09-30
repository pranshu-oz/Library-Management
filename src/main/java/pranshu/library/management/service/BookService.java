package pranshu.library.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import pranshu.library.management.model.Book;
import pranshu.library.management.model.User;
import pranshu.library.management.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	public void saveNewBook(Book book) {
		book.setAvailableCopy(book.getTotalCopy());
		bookRepository.save(book);
	}

	public Page<Book> findAllBookByFilter(String filter, Pageable pageable) {

		return bookRepository.findAllBookByFilter(filter, pageable);
	}

	public Book findBookById(long id) {

		return bookRepository.findById(id).orElse(null);
	}

	public void deleteBookById(long id) {
		
		bookRepository.deleteById(id);
	}

	public List<Book> findAllBooks() {
		
		return bookRepository.findAll();
	}

}
