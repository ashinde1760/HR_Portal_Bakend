package com.project.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.User;
import com.project.repo.UserRepository;
import com.project.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/")
	public List<User> getAllUsers(){

		return this.userService.getAllUsers();

	}

	@GetMapping("/{userId}")
	public User getUser(@PathVariable("userId") int id) {

		 Optional<User> user = this.userRepository.findById(id);
		 return user.get();
	}
	
	
	@PostMapping("/change_password")
	public void changepassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, Principal principal) throws Exception
	{
	
		
		String username = principal.getName();
        User user = this.userRepository.findByUsername(username);
        System.out.println("user" + user.toString());
        if(this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
			//change password
        	   System.out.println("username........"+ user.getUsername());
			user.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
			this.userRepository.save(user);
        }
        else{
        	throw new Exception("Please Enter Correct Password!!");
        }
      
		
		
	}
}
