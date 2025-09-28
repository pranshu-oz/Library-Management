package pranshu.library.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("loans")
public class LoanController {

	@GetMapping("")
	public String showStatus() {
		
		return "loans";
	}
	
	@GetMapping("/reports")
	public String showReports() {
		
		return "reports";
	}
}
