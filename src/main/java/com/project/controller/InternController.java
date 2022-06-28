package com.project.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.entity.Employee;
import com.project.entity.Intern;
import com.project.entity.Role;
import com.project.entity.User;
import com.project.entity.UserRole;
import com.project.repo.InternRepository;
import com.project.service.InternService;
import com.project.service.UserService;

@RestController
@RequestMapping("/intern")
@CrossOrigin("*")
public class InternController {

	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private InternService internService;
	@Autowired
	private InternRepository internRepository;


	//Registration Intern
	@PostMapping("/register")
	public User createUser(@RequestBody User user) throws Exception {
		System.out.println("inside intern controller.............");
		//encoding password with bcryptpasswordEncoder
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		Set<UserRole> roles = new HashSet<>();
		Role role = new Role();
		role.setRoleId(46);
		role.setName("INTERN");
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		roles.add(userRole);
		return this.userService.createUser(user, roles);


		}

	@GetMapping("/{id}")
	public Intern getEmployee(@PathVariable("id") int id) {

		return this.internService.getIntern(id);
	}

	@GetMapping("/load")
	public List<Intern> laodAllEmployee(){
		return this.internService.getAllIntern();

	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable("id") int id) {
		this.internService.deleteIntern(id);

	}

	@GetMapping("/all")
	public List<User> loadAllUser(){

		String role = "INTERN";

		return this.userService.getAllUsers(role);

	}

	@PutMapping("/{id}")
	public Intern updateIntern(@RequestBody Intern intern,@PathVariable("id") int id) {
		Optional<Intern> optionalIntern = this.internRepository.findById(id);
         if(!optionalIntern.isPresent()) {
        	 new Exception("intern with id not present");
         }

        Intern intern1 = optionalIntern.get();
         intern1.setName(intern.getName());
         intern1.setMobile(intern.getMobile());
         intern1.setEmail(intern.getEmail());
         intern1.setMentor(intern.getMentor());
         intern1.setLocation(intern.getLocation());
         intern1.setProjectname(intern.getProjectname());
         intern1.setActionTaken(intern.getActionTaken());
         intern1.setReview(intern.getReview());
         intern1.setAddress(intern.getAddress());
        
         intern1.setDOJ(intern.getDOJ());
         intern1.setDOL(intern.getDOL());
	return this.internService.updateIntern(intern1);
	}
       
	
	@PutMapping("/upload/{id}")
    public String uplaodProfilePic(@RequestParam("profilePic") MultipartFile file,@PathVariable("id") int id)
    {
     return this.internService.UploadProfilePic(file, id);
    }

}
