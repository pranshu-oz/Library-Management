package pranshu.library.management.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pranshu.library.management.service.BookService;
import pranshu.library.management.service.LoanService;
import pranshu.library.management.service.UserService;
import pranshu.library.management.util.JwtUtil;

@Controller
public class DashBoardController {
	
	@Autowired
	UserService userService;
	
	@Autowired 
	BookService bookService;
	
	@Autowired 
	LoanService loanService;
	
		
	Map<String,List<Object>> map;
	
	@GetMapping("/dashboard")
	public String getDashBoard(Model model) {
		
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("totalMembers", userService.getTotalUsers());
		model.addAttribute("totalBooks", bookService.getTotalBooks());
		model.addAttribute("totalLoans", loanService.getTotalLoans());
		model.addAttribute("user", username);
		model.addAttribute("overdue", loanService.getTotalOverdue());
		model.addAttribute("activePage", "dashboard");
		return "dashboard";
	}
	
	
	@GetMapping("/book/week-stats")
	public ResponseEntity<Map<String,List<Object>>> getWeeklyStats() {
		
		map=bookService.getCurrentWeekStats();
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/book/available-stats")
	public ResponseEntity<Map<String,List<Object>>> getAvailableStats(){
		
		
		map=bookService.getAvailableStats();
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/book/category")
	public ResponseEntity<Map<String,List<Object>>> getTotalBookCategory(){
		
		map=bookService.getTotalBookCategory();
		
		return ResponseEntity.ok(map);
	}
	
	
	
	@GetMapping("library/total-user")
	public ResponseEntity<Map<String,List<Object>>> getTotalUserByMemberShip(){
		
		map=userService.getTotalUserByMemberShip();
		
		return ResponseEntity.ok(map);
	}
	
}
