package pranshu.library.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashBoardController {
	
	@GetMapping("")
	public String getDashBoard(Model model) {
		
		model.addAttribute("activePage", "dashboard");
		return "dashboard";
	}
	

}
