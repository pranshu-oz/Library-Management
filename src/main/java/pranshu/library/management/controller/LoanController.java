package pranshu.library.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import pranshu.library.management.model.Loans;
import pranshu.library.management.model.Loans.Status;
import pranshu.library.management.service.BookService;
import pranshu.library.management.service.LoanService;
import pranshu.library.management.service.UserService;

@Controller
@RequestMapping("loans")
public class LoanController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	LoanService loanService;

	@GetMapping("")
	public String showStatus(@RequestParam(defaultValue="Borrowed") Status status,
							@RequestParam(required=false) String search,Model model) {
		
		model.addAttribute("activePage", "loans");
		model.addAttribute("loans", new Loans());
		model.addAttribute("loansList",loanService.findFilteredLoan(status,search));
		model.addAttribute("users",userService.findAllUsers());
		model.addAttribute("books", bookService.findAllBooks());
		return "loans";
	}
	
	@PostMapping("/add")
	public String addNewLoan(@Valid @ModelAttribute("loans") Loans loans, BindingResult result, RedirectAttributes redirect) {
	
		if(result.hasErrors()) {
			System.out.println("Error........: "+result.getAllErrors());
		}
		
		String str=loanService.addNewLoan(loans);
		if(str!=null) {
			redirect.addFlashAttribute("success", str);
			return "redirect:/loans";
		}
		redirect.addFlashAttribute("success", "new loan has added...");
		return "redirect:/loans";
	}
	
	// For Return book and manage loans 
	
	@PostMapping("/return")
	public String returnBookManageLoan(@ModelAttribute("loans") Loans loan,BindingResult result, RedirectAttributes redirect) {
		
		if(result.hasErrors()) {
			System.out.println("Error----------------- :"+result.getAllErrors());
		}
		long fine=loanService.returnBook(loan);
		
		if(fine==0) {
			redirect.addFlashAttribute("success","Book has returned within Due date Not fine Needed ... ");
			return "redirect:/loans";
		}
		redirect.addFlashAttribute("success","Book has returned And Fine is .. "+fine+"Rs.");
		return "redirect:/loans";
	}
	
	
	
	
	//report mapping for report to control.
	@GetMapping("/reports")
	public String showReports(Model model) {
		
		model.addAttribute("activePage", "reports");
		return "reports";
	}
	
	
}
