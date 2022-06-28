package com.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.service.UserService;

@SpringBootApplication
public class AnemoieEmpPortalApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(AnemoieEmpPortalApplication.class, args);

		System.out.println("starting project.......");


	}

	@Override
	public void run(String... args) throws Exception {

//		User user = new User();
//		user.setUsername("jay");
//		user.setPassword(passwordEncoder.encode("jay@123"));
//		Role role1 = new Role();
//		role1.setRoleId(44);
//		role1.setName("ADMIN");
//
//		Set<UserRole> userRoleSet = new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//		userRoleSet.add(userRole);
//		this.userService.createUser(user, userRoleSet);
//

	}

}
