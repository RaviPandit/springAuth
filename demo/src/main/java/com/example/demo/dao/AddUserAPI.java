package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/1")
public class AddUserAPI {

	@Autowired
	private UserService userRepository;

	@PostMapping(path="/users" , consumes = "application/xml")
	public int createUser(@RequestBody User user) {
		return userRepository.addEmployee(user);
	}

}
