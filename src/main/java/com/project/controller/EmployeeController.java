package com.project.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;

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
import com.project.entity.Role;
import com.project.entity.User;
import com.project.entity.UserRole;
import com.project.repo.EmployeeRepository;
import com.project.repo.UserRepository;
import com.project.service.UserService;

@RestController
@RequestMapping("/emp")
@CrossOrigin("*")
public class EmployeeController {

	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private UserRepository userRepository;


	EntityManager entityManager;

	//Registration Employee
	@PostMapping("/register")
	public User createUser(@RequestBody User user) throws Exception {
		System.out.println("inside	 employee controller.............");
		//encoding password with bcryptpasswordEncoder
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		Set<UserRole> roles = new HashSet<>();
		Role role = new Role();
		role.setRoleId(45);
		role.setName("EMPLOYEE");
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		roles.add(userRole);
		return this.userService.createUser(user, roles);


		}

	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable("id") int id) {

		return this.userService.getEmployee(id);
	}

	@GetMapping("/load")
	public List<Employee> laodAllEmployee(){
		return this.userService.getAllEmployee();

	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable("id") int id) {
		this.userService.deleteEmployee(id);

	}

	@GetMapping("/all")
	public List<User> loadAllUser(){

		String role = "EMPLOYEE";

		return this.userService.getAllUsers(role);

	}

	@PutMapping("/{id}")
	public Employee updateEmployee(@RequestBody Employee employee,@PathVariable("id") int id) {
		Optional<Employee> OptionalEmployee = this.employeeRepository.findById(id);
         if(!OptionalEmployee.isPresent()) {
        	 new Exception("intern with id not present");
         }
         Employee employee1 = OptionalEmployee.get();
         employee1.setName(employee.getName());
         employee1.setMobile(employee.getMobile());
         employee1.setEmail(employee.getEmail());
         employee1.setDesignation(employee.getDesignation());
         employee1.setLocation(employee.getLocation());
         employee1.setDepartment(employee.getDepartment());
         employee1.setActionTaken(employee.getActionTaken());
         employee1.setReview(employee.getReview());
         employee1.setCurrentAddress(employee.getCurrentAddress());
         employee1.setPermanentAddress(employee.getPermanentAddress());
         employee1.setDOJ(employee.getDOJ());
         employee1.setDOL(employee.getDOL());
         employee1.setReportingTo(employee.getReportingTo());
	return this.userService.updateEmployee(employee1);
	}


	@PutMapping("/update")
	public User updateUser(@RequestBody User user)
	{

		return  this.userService.UpdateUser(user);
	}

	@PostMapping("/")
	public Employee registerEmployee(@RequestBody Employee employee)
	{
		return this.userService.registerEmployee(employee);
	}


    @PutMapping("/upload/{id}")
     public String uplaodProfilePic(@RequestParam("profilePic") MultipartFile file,@PathVariable("id") int id)
     {
      return this.userService.UploadProfilePic(file, id);
     }



}
