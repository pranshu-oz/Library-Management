package pranshu.library.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import pranshu.library.management.model.User;
import pranshu.library.management.service.BookService;
import pranshu.library.management.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@GetMapping("")
	public String showMembers(@RequestParam(defaultValue="0") int page,
								@RequestParam(defaultValue="10") int size,
								@RequestParam(required=false) String filter,Model model) {
		
		Pageable pageable=PageRequest.of(page, size);
		Page<User> users=userService.findAllUserByFilter(filter,pageable);
		
		model.addAttribute("users", users);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", users.getTotalPages());
		model.addAttribute("activePage", "user");
		return "members";
	}
	
	@GetMapping("/add")
	public String addNewUser(Model model) {
		
		model.addAttribute("users", new User());
		model.addAttribute("activePage", "user");
		return "add-member";
	}
	
	@PostMapping("/add")
	public String saveNewUser(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes redirect) {
		
		if(result.hasErrors()) {
			System.out.println("Error :"+result.getAllErrors());
		}
		userService.saveNewUser(user);
		redirect.addFlashAttribute("success", "New User Has been Added");
		return "redirect:/user";
	}
}
