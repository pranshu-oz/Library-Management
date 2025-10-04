package pranshu.library.management.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import pranshu.library.management.model.RoleManager;
import pranshu.library.management.service.RoleManagerService;
import pranshu.library.management.util.JwtUtil;
@Controller
public class AuthenticationController {
	
	@Autowired
	JwtUtil jwtUtil;

	private String token;

	@Autowired
	private RoleManagerService roleManagerService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	

	
	@PostMapping("/edit/user")
	public String loginPage(@Valid @ModelAttribute("roles") RoleManager roleManager,
							BindingResult result, RedirectAttributes redirect){	

		if(result.hasErrors()) {
			System.out.println("Error :"+result.getAllErrors());
		}
		roleManagerService.saveNewUser(roleManager);
		redirect.addFlashAttribute("success", "user saved");
		
		return "add-new-role";
		
	}
	
	@GetMapping("/edit/user")
	public String newRole(Model model) {
		
		model.addAttribute("roles", new RoleManager());
		return "add-new-role";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		
		model.addAttribute("roles", new RoleManager());
		return "login";
	}
	
	@PostMapping("/login")
	public String authentication(@ModelAttribute("roles") RoleManager roles, 
								RedirectAttributes redirect,
								HttpServletResponse response) {
	
		try {
		Authentication auth=authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(roles.getUsername(),roles.getPassword()));
		this.token=jwtUtil.generateToken(auth.getName());
		System.out.println("token generated : "+token);
		redirect.addFlashAttribute("success", "login Successfull");
		
		//Adding Coockie to header for authentication
		ResponseCookie cookie = ResponseCookie.from("jwt", token)
	            .httpOnly(true)      // secure, JS can’t read
	            .path("/")           // valid for all routes
	            .maxAge(24 * 60 * 60) // 1 day
	            .build();
	    response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
		
		return "redirect:/dashboard";
		}catch(AuthenticationException e) {return "Invalid Credential";}
		
	}
	
	@GetMapping("/auth/logout")
	public String logout(HttpServletResponse response)throws ServletException, IOException {
		
		ResponseCookie cookie = ResponseCookie.from("jwt", "")
	            .httpOnly(true)             // same as login
	            .path("/")                  // must match login
	            .maxAge(0)                  // expire immediately
	            .build();
		
		response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
		return "redirect:/login";
	}
	
	
	
	
	
}
