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
import com.project.entity.Report;
import com.project.repo.EmployeeRepository;
import com.project.repo.InternRepository;
import com.project.repo.ReportRepository;
import com.project.service.ReportService;

@RestController
@RequestMapping("/report")
@CrossOrigin("*")
public class ReportController {

	@Autowired
	private ReportService reportService;
	 @Autowired
	 private EmployeeRepository employeeRepository;
	 @Autowired
 private InternRepository internRepository;
	 @Autowired
	 private ReportRepository reportRepository;



	@PostMapping("/{id}")
	public ResponseEntity<Report> addReportToEmployee(@PathVariable("id") int id,@RequestBody Report report)
	{

		try {
			report = this.reportService.addReportToEmployee(id, report);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return ResponseEntity.ok(report);
	}

	@GetMapping("/{id}")
	public Report getleave(@PathVariable("id") int id)
	{
		return this.reportService.getReport(id);
	}

	//getallleave
	@GetMapping("/getall")
	public ResponseEntity<?> getAllLeaves()
	{
		return ResponseEntity.ok(this.reportService.getReports());
	}

	@PostMapping("/intern/{id}")
	public ResponseEntity<Report> addreportToIntern(@PathVariable("id") int id,@RequestBody Report report)
	{

		try {
			report = this.reportService.addReportToIntern(id, report);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return ResponseEntity.ok(report);
	}

	@DeleteMapping("/{id}")
	public void deleteReport(@PathVariable("id") int id)
	{
		this.reportService.deleteReport(id);
	}

	//update

    @PutMapping("/{id}/report/{employeeId}")
    public ResponseEntity<Report> update(@RequestBody Report report , @PathVariable("id") int id,@PathVariable("employeeId") int empId) {
//      Optional<Employee> optionalEmployee = employeeRepository.findById(leave.getEmployee().getId());
    	Optional<Employee> employee = employeeRepository.findById(empId);
    	Optional<Intern> intern = this.internRepository.findById(empId);
    	Optional<Report> optionalReport = reportRepository.findById(id);

        if (employee.isPresent()) {
        	   report.setEmployee(employee.get());
        }
        else if(intern.isPresent()) {
        	   report.setIntern(intern.get());
        }

        else  {
            return ResponseEntity.unprocessableEntity().build();
        }


        report.setId(optionalReport.get().getId());
        reportRepository.save(report);

        return ResponseEntity.noContent().build();
 }





}
