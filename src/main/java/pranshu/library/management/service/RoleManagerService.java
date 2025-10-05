package pranshu.library.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pranshu.library.management.model.RoleManager;
import pranshu.library.management.repository.RoleManagerRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RoleManagerService implements UserDetailsService {
	
	@Autowired
	RoleManagerRepository roleMangerRepository;
	
	PasswordEncoder encode=new BCryptPasswordEncoder();
	
	public void saveNewUser(RoleManager roleManager) {

		roleManager.setRoles("User");
		roleManager.setPassword(encode.encode(roleManager.getPassword()));
		roleMangerRepository.save(roleManager);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RoleManager roleManager= roleMangerRepository.findByUsername(username).orElse(null);
		
		return User.withUsername(roleManager.getUsername())
				.password(roleManager.getPassword()).build();
	}
	
	public List<RoleManager> findAllUsers(){
		
		return roleMangerRepository.findAll();
	}

	public void deleteUserById(long id) {
		
		roleMangerRepository.deleteById(id);
	}

}
