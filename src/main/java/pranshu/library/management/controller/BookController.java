package pranshu.library.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import pranshu.library.management.model.Book;
import pranshu.library.management.repository.BookRepository;
import pranshu.library.management.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	BookRepository bookRepository;

	@GetMapping("")
	public String showBooks( Model model) {
		List<Book> books=bookRepository.findAll();
		model.addAttribute("books", books);
		model.addAttribute("activePage", "book");
		return "book";
	}
	
	@GetMapping("/add")
	public String showAddBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("activePage","book");
		return "add-book";
	}
	@PostMapping("/add")
	public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result,RedirectAttributes redirect) {
		if(result.hasErrors()) {
			System.out.println("Error"+result.getAllErrors());
		}
		bookService.saveNewBook(book);
		redirect.addFlashAttribute("success","New book has been registered");
		
		return "book";
	}
	
}
