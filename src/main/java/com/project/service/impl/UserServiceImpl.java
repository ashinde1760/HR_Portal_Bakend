package com.project.service.impl;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.entity.Employee;
import com.project.entity.User;
import com.project.entity.UserRole;
import com.project.repo.EmployeeRepository;
import com.project.repo.RoleRepository;
import com.project.repo.UserRepository;
import com.project.service.UserService;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
    private EmployeeRepository employeeRepository;



	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {

		User local = this.userRepository.findByUsername(user.getUsername());

       if(local!=null)
       {
    	   System.out.println("User already there!!");
    	   throw new Exception("User already present!!");
       }else
       {
    	   for(UserRole ur:userRoles) {

    		   this.roleRepository.save(ur.getRole());

    		   user.getUserroles().addAll(userRoles);
    		    local = this.userRepository.save(user);
    	   }

       }

		return local;
		}

//getting employee by email
	@Override
	public Employee getEmployee(int id) {
		return this.employeeRepository.findById(id).get();


	}

	//get all employees
	@Override
	public List<Employee> getAllEmployee() {

		return this.employeeRepository.findAll();
	}



	//delete employee by id

	@Override
	public void deleteEmployee(int id) {

		this.employeeRepository.deleteById(id);

	}

	@Override
	public List<User> getAllUsers(String rolename) {
		return this.userRepository.findBySpecificRoles(rolename);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return this.employeeRepository.save(employee);
	}

	@Override
	public User UpdateUser(User user) {

		return this.userRepository.save(user);
	}

	@Override
	public Employee registerEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return this.employeeRepository.save(employee);
	}

	@Override
	public List<User> getAllUsers() {

		return this.userRepository.findAll();
	}

	@Override
	public String UploadProfilePic(MultipartFile file, int id) {
		
		Optional<Employee> OptionalEmployee = this.employeeRepository.findById(id);
        if(!OptionalEmployee.isPresent()) {
       	 new Exception("Employee with id not present");
        }
        
        Employee employee1 = OptionalEmployee.get();

        try {
        	if(employee1.getProfilePic() !=null) {
        		employee1.setProfilePic(null);
        	}
        		
			employee1.setProfilePic(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
        this.employeeRepository.save(employee1);
        
        return "file uploaded!!!!";

	}

}
