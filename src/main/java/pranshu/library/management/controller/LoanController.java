package pranshu.library.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("loans")
public class LoanController {

	@GetMapping("")
	public String showStatus(Model model) {
		
		model.addAttribute("activePage", "loans");
		return "loans";
	}
	
	@GetMapping("/reports")
	public String showReports(Model model) {
		
		model.addAttribute("activePage", "reports");
		return "reports";
	}
}
