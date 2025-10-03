package pranshu.library.management.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import pranshu.library.management.model.Book;
import pranshu.library.management.repository.BookCategoryRepository;
import pranshu.library.management.repository.BookRepository;
import pranshu.library.management.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired 
	BookCategoryRepository bookCategoryRepository;

	@GetMapping("")
	public String showBooks(@RequestParam(defaultValue="0") int page,
							@RequestParam(defaultValue="10") int size,
							@RequestParam(required=false) String filter, Model model) {
		
		Pageable pageable =PageRequest.of(page, size);
		Page<Book> books=bookService.findAllBookByFilter(filter,pageable);
		
		model.addAttribute("books", books);
		model.addAttribute("activePage", "book");
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", books.getTotalPages());
		return "book";
	}
	
	@GetMapping("/add")
	public String showAddBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", bookCategoryRepository.findAll());
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
		
		return "redirect:/book";
	}
	
	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable("id") long id, Model model) {
		
		Book book=bookService.findBookById(id);
		model.addAttribute("book", book);
		model.addAttribute("activePage", "book");
		
		if(book!=null) {
			return "edit-book";
		}else return "book";
	}
	
	@PostMapping("/edit")
	public String updateBook(@Valid @ModelAttribute("book") Book book, BindingResult result, RedirectAttributes redirect){
		
		if(result.hasErrors()) {
			System.out.println("Error :"+result.getAllErrors());
		}
		bookService.saveNewBook(book);
		redirect.addFlashAttribute("success", "Book has been updated");
		return "redirect:/book";
		
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") long id, RedirectAttributes redirect) {
		
		bookService.deleteBookById(id);
		redirect.addFlashAttribute("success", "Book has been Deleted from Library");
		return "redirect:/book";
	}
	
}
