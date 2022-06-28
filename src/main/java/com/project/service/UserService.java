package com.project.service;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.project.entity.Employee;
import com.project.entity.User;
import com.project.entity.UserRole;

public interface UserService {

	//creating user
	public User createUser(User user,Set<UserRole> userRoles) throws Exception;

	//get employee by email
	public Employee getEmployee(int id);

	//load all employee

	public List<Employee> getAllEmployee();

	//delete employee
	public void deleteEmployee(int id);

	//getallUsers
	public List<User> getAllUsers(String rolename);

	//update Employee
	public Employee updateEmployee(Employee employee);

	//update User
	public User UpdateUser(User user);

	//create Employee
	public Employee registerEmployee(Employee employee);


	//get all users
	public List<User> getAllUsers();



  //uploading profile pic to employee
	public String UploadProfilePic(MultipartFile file, int id);


}
