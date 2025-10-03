package pranshu.library.management.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public Map<String, List<Object>> getTotalUserByMemberShip() {
		List<Object[]> result=userRepository.getTotalUserByMemberShip();
		
		ArrayList<Object> member=new ArrayList();
		ArrayList<Object> user=new ArrayList();
		
		for(Object[] r : result) {
			member.add(r[0]);
			user.add(r[1]);
		}
		
		Map<String,List<Object>> map= new HashMap();
		map.put("member", member);
		map.put("user", user);
		return map;
	}

}
