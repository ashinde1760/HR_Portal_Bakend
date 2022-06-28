package com.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.entity.Employee;
import com.project.entity.Intern;
import com.project.entity.Leaves;
import com.project.repo.EmployeeRepository;
import com.project.repo.InternRepository;
import com.project.repo.LeaveRepository;
import com.project.service.LeaveService;


@RestController
@RequestMapping("/leave")
@CrossOrigin("*")
public class LeaveController {

	@Autowired
	private LeaveService leaveService;
	@Autowired
	private LeaveRepository leaveRepository;
	 @Autowired
	 private EmployeeRepository employeeRepository;
	 @Autowired
 private InternRepository internRepository;

	//add leave
	@PostMapping("/{id}")
	public ResponseEntity<Leaves> addleaveToEmployee(@PathVariable("id") int id,@RequestBody Leaves leave)
	{

		try {
			leave = this.leaveService.addLeaveToEmployee(id, leave);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return ResponseEntity.ok(leave);
	}

	//get leave
	@GetMapping("/{id}")
	public Leaves getleave(@PathVariable("id") int leaveId)
	{
		return this.leaveService.getLeave(leaveId);
	}

	//getallleave
	@GetMapping("/getall")
	public ResponseEntity<?> getAllLeaves()
	{
		return ResponseEntity.ok(this.leaveService.getLeaves());
	}

    //update

	    @PutMapping("/{id}/leave/{employeeId}")
	    public ResponseEntity<Leaves> update(@RequestBody Leaves leave , @PathVariable("id") int id,@PathVariable("employeeId") int empId) {
//	        Optional<Employee> optionalEmployee = employeeRepository.findById(leave.getEmployee().getId());
	    	Optional<Employee> employee = employeeRepository.findById(empId);
	    	Optional<Intern> intern = this.internRepository.findById(empId);
	    	Optional<Leaves> optionalLeaves = leaveRepository.findById(id);
	    	Leaves leave1 = optionalLeaves.get();
	        String leaveType = "Casual";
	        String grantedOption = "Accepted";
              String leaveType1 = "Earned";
              String leaveType2 = "Medical";
	    	   if (employee.isPresent()) {
		        	Employee employee1 =employee.get();
		        	System.out.println(  "nol.........." +leave.getNumberOfLeave());
		        	
		        	
		        		
		        	
		        	 if(leave1.getLeavetype().equals(leaveType) && leave.getGrantedOption().equals(grantedOption)) {
		        	employee1.setTotalCasualLeaves(employee1.getTotalCasualLeaves() + leave.getNumberOfLeave());
		        	System.out.println("totalcasualalEaves......" + employee1.getTotalCasualLeaves());
		            } 
		        	 else if(leave1.getLeavetype().equals(leaveType1) && leave.getGrantedOption().equals(grantedOption))
		        	 {
		        		 employee1.setTotalEarnedLeaves(employee1.getTotalEarnedLeaves() + leave.getNumberOfLeave());
		        		 
		        		 System.out.println("totalEarnedLeaves............." + employee1.getTotalEarnedLeaves());
		        		 
		        	 }
		        	 else if(leave1.getLeavetype().equals(leaveType2) && leave.getGrantedOption().equals(grantedOption))
		        	 {
		        		 employee1.setTotalSickLeaves(employee1.getTotalSickLeaves() + leave.getNumberOfLeave());
		        		 
		        		 System.out.println("totalEarnedLeaves............." + employee1.getTotalEarnedLeaves());
		        		 
		        	 }
		        }
	        else if(intern.isPresent()) {
	        	Intern intern1 = intern.get();
	        	if(leave.getGrantedOption().equals(grantedOption)) {
	        		   intern1.setTotalLeave(intern1.getTotalLeave() + leave.getNumberOfLeave());
	        	}
	        }

	        else  {
	            return ResponseEntity.unprocessableEntity().build();
	        }


	       optionalLeaves.get().setGrantedOption(leave.getGrantedOption());
	       optionalLeaves.get().setComment(leave.getComment());
	        leaveRepository.save(leave1);

	        return ResponseEntity.noContent().build();
	 }

	//delete leave
	@DeleteMapping("/{id}")
	public String deleteLeave(@PathVariable("id") int id)
	{
		 String leaveType = "Casual";
	        String grantedOption = "Accepted";
           String leaveType1 = "Earned";
           String leaveType2 = "Medical";
		Optional<Leaves> OptionalLeaves = this.leaveRepository.findById(id);
		Leaves leave = OptionalLeaves.get();
		int empId = leave.getEmployeeId();
		Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
    	Optional<Intern> optionalIntern = this.internRepository.findById(empId);
	
		if(optionalEmployee.isPresent() && leave.getGrantedOption() != null) {
		     
			Employee employee = optionalEmployee.get();
			if(leave.getGrantedOption().equals(grantedOption) && leave.getLeavetype().equals(leaveType)) {
				employee.setTotalCasualLeaves(employee.getTotalCasualLeaves() - leave.getNumberOfLeave());
			}
			else if(leave.getGrantedOption().equals(grantedOption) && leave.getLeavetype().equals(leaveType1))
			{
				employee.setTotalEarnedLeaves(employee.getTotalEarnedLeaves() - leave.getNumberOfLeave());
			}
			else if(leave.getGrantedOption().equals(grantedOption) && leave.getLeavetype().equals(leaveType2))
			{
				employee.setTotalSickLeaves(employee.getTotalSickLeaves() - leave.getNumberOfLeave());
			}
		}
		else if(optionalIntern.isPresent() && leave.getGrantedOption()!= null)
		{
			Intern intern = optionalIntern.get();
			intern.setTotalLeave(intern.getTotalLeave() - leave.getNumberOfLeave());
		}
		
		this.leaveService.deleteLeaves(id);
		return "leave Deleted";
	}

//
//	@PutMapping("/update/{id}")
//	 public Leaves upateLeaves(@PathVariable("id") int id, @RequestBody Leaves leave) {
//		 List<Leaves> leaves = new ArrayList<>();
//	        Employee employee1 = new Employee();
//
//	        Optional<Employee> byId = this.employeeRepository.findById(id);
//	        try {
//				if (!byId.isPresent()) {
//				    throw new Exception("Employee with id " + id + " does not exist");
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	        Employee employee = byId.get();
//
//	        //tie Employee to leave
//	        leave.setEmployee(employee);
//
//	        Leaves leave1 = this.leaveRepository.save(leave);
//	        //tie leave to employee
//	        leaves.add(leave1);
//	        employee1.setLeavelist(leaves);;
//
//	        return leave1;
//
//
//	}

	//add leaves in Intern

	@PostMapping("/intern/{id}")
	public ResponseEntity<Leaves> addleaveToIntern(@PathVariable("id") int id,@RequestBody Leaves leave)
	{

		try {
			leave = this.leaveService.addLeaveToIntern(id, leave);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return ResponseEntity.ok(leave);
	}
	

}
