package pranshu.library.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import pranshu.library.management.model.User;
import pranshu.library.management.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public void saveNewUser(User user) {
		
		userRepository.save(user);
		
	}

	public Page<User> findAllUserByFilter(String filter, Pageable pageable) {
		
		return userRepository.findAllUserByFilter(filter,pageable);
	}

	public User findUserById(long id) {
		
		return userRepository.findById(id).orElse(null);
	}

	public void deleteUserById(long id) {
	
		userRepository.deleteById(id);
	}

	public List<User> findAllUsers() {

		return userRepository.findAll();
	}

	public long getTotalUsers() {
	
		return userRepository.getTotalUser();
	}

}
