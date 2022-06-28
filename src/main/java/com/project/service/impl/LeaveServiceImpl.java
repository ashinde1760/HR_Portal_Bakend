package com.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Employee;
import com.project.entity.Intern;
import com.project.entity.Leaves;
import com.project.repo.EmployeeRepository;
import com.project.repo.InternRepository;
import com.project.repo.LeaveRepository;
import com.project.repo.UserRepository;
import com.project.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private LeaveRepository leaverepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private InternRepository internRepository;


	@Override
	public List<Leaves> getLeaves() {

		return this.leaverepository.findAll();
	}

	@Override
	public Leaves getLeave(int Id) {

		return this.leaverepository.findById(Id).get();
	}

	@Override
	public void deleteLeaves(int id) {

		this.leaverepository.deleteById(id);

	}


	@Override
	public Leaves updateLeave(Leaves leave){


		return this.leaverepository.save(leave);

	}

	@Override
	public Leaves addLeaveToEmployee(int id, Leaves leave) throws Exception {

		 List<Leaves> leaves = new ArrayList<>();
	        Employee employee1 = new Employee();

	        Optional<Employee> byId = this.employeeRepository.findById(id);
	        try {
				if (!byId.isPresent()) {
				    throw new Exception("Author with id " + id + " does not exist");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Employee employee = byId.get();

	        //tie Employee to leave
	        leave.setEmployee(employee);
	       
//             leave.getEmployee().setTotalCasualLeaves(4);
	        
	        Leaves leave1 = this.leaverepository.save(leave);
	        //tie leave to employee
	        leaves.add(leave1);
	        employee1.setLeavelist(leaves);

	        return leave1;

	    }

	@Override
	public Leaves addLeaveToIntern(int id, Leaves leave) throws Exception {
		 List<Leaves> leaves = new ArrayList<>();
	        Intern intern1 = new Intern();

	        Optional<Intern> byId = this.internRepository.findById(id);
	        try {
				if (!byId.isPresent()) {
				    throw new Exception("Intern with id " + id + " does not exist");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Intern intern = byId.get();

	        //tie Employee to leave
	        leave.setIntern(intern);
	        Leaves leave1 = this.leaverepository.save(leave);
	        //tie leave to employee
	        leaves.add(leave1);
	        intern1.setLeaveList(leaves);
           
            
	        return leave1;

	}
	}




