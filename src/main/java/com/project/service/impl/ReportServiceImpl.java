package com.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Employee;
import com.project.entity.Intern;
import com.project.entity.Report;
import com.project.repo.EmployeeRepository;
import com.project.repo.InternRepository;
import com.project.repo.ReportRepository;
import com.project.repo.UserRepository;
import com.project.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private InternRepository internRepository;
	@Override


	public Report addReportToEmployee(int id, Report report) throws Exception {

		List<Report> reportList = new ArrayList<>();
	        Employee employee1 = new Employee();

	        Optional<Employee> byId = this.employeeRepository.findById(id);
	        try {
				if (!byId.isPresent()) {
				    throw new Exception("Employee with id " + id + " does not exist");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Employee employee = byId.get();

	        //tie Employee to leave
	        report.setEmployee(employee);

	        Report report1 = this.reportRepository.save(report);
	        //tie leave to employee
	        reportList.add(report1);
	        employee1.setReportList(reportList);

	        return report1;

	}
	@Override
	public Report addReportToIntern(int id, Report report) throws Exception {
		 List<Report> reportList = new ArrayList<>();
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
	        report.setIntern(intern);

	       Report report1 = this.reportRepository.save(report);
	        //tie leave to employee
	        reportList.add(report1);
	        intern1.setReportList(reportList);

	        return report1;
	}
	@Override
	public Report updateReport(Report report) {
		return this.reportRepository.save(report);
	}
	@Override
	public List<Report> getReports() {
		return this.reportRepository.findAll();
	}
	@Override
	public Report getReport(int Id) {
		return this.reportRepository.findById(Id).get();
	}
	@Override
	public void deleteReport(int id) {
		this.reportRepository.deleteById(id);

	}



}
