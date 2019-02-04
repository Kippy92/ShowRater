package com.codingdojo.exam.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.exam.models.Rating;
import com.codingdojo.exam.models.Show;
import com.codingdojo.exam.models.User;
import com.codingdojo.exam.repositories.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User createUser(Map<String, String> body) {
		User user = new User(body);
		this.userRepository.save(user);
		return user;
	}
	
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	public User findUser(long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}
		else {
			return null;
		}
	}
	public void addUserToShow(User u, Show s) {	
		u.setShow(s);
		this.userRepository.save(u);
	}
	
}