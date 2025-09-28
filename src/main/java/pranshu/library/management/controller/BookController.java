package pranshu.library.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {

	@GetMapping("")
	public String showBooks( Model model) {
		
		model.addAttribute("activePage", "book");
		return "book";
	}
	
}
