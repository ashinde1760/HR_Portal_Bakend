package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

 @Entity
 public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String feedback;
	private String actionTaken;

	@JsonBackReference(value = "employee-report")
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee employee;

	@JsonBackReference(value = "intern-report")
	@ManyToOne(fetch = FetchType.LAZY)
	private Intern intern;


	public Report() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getActionTaken() {
		return actionTaken;
	}

	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Intern getIntern() {
		return intern;
	}

	public void setIntern(Intern intern) {
		this.intern = intern;
	}

	public String getEmployeeName() {
//		return this.employee.getName();
        if(this.employee == null) {
        	 return this.intern.getName();
        }
        return this.employee.getName();


	}

	public String getUsername() {
		if(this.employee == null) {
			return this.intern.getUser().getUsername();
		}
		return this.employee.getUser().getUsername();
	}


	public int getEmpId() {
		if(this.employee == null) {
			return this.intern.getId();
		}
		return this.employee.getId();
	}




}
