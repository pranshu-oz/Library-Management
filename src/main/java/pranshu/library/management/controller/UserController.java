package pranshu.library.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("")
	public String showMembers(Model model) {
		
		model.addAttribute("activePage", "user");
		return "members";
	}
}
