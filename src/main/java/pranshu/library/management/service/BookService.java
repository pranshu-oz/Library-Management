package pranshu.library.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pranshu.library.management.model.Book;
import pranshu.library.management.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	public void saveNewBook(Book book) {
		book.setAvailableCopy(book.getTotalCopy());
		bookRepository.save(book);
	}
}
