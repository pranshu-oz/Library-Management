package pranshu.library.management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pranshu.library.management.service.BookService;
import pranshu.library.management.service.LoanService;
import pranshu.library.management.service.UserService;

@Controller
public class DashBoardController {
	
	@Autowired
	UserService userService;
	
	@Autowired 
	BookService bookService;
	
	@Autowired 
	LoanService loanService;
	
	@GetMapping("")
	public String getDashBoard(Model model) {
		
		model.addAttribute("totalMembers", userService.getTotalUsers());
		model.addAttribute("totalBooks", bookService.getTotalBooks());
		model.addAttribute("totalLoans", loanService.getTotalLoans());
		model.addAttribute("overdue", loanService.getTotalOverdue());
		
		model.addAttribute("activePage", "dashboard");
		return "dashboard";
	}
	
	@GetMapping("/book/week-stats")
	public ResponseEntity<Map<String,List<Object>>> getWeeklyStats() {
		
		Map<String,List<Object>> map=new HashMap<>();
		map=bookService.getCurrentWeekStats();
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/book/available-stats")
	public ResponseEntity<Map<String,List<Object>>> getAvailableStats(){
		
		Map<String,List<Object>> map=new HashMap<>();
		map=bookService.getAvailableStats();
		return ResponseEntity.ok(map);
	}

}
