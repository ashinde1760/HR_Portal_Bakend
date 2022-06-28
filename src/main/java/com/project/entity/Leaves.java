package com.project.entity;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Leaves {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String name;
	private int numberOfLeave;
	private Date fromDate;
	private Date toDate;
	private String leavetype;
	private String reason;
	private String grantedOption;
	private String comment;


	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference(value = "employee-leaves")
	private Employee employee;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference(value = "intern-leave")
	private Intern intern;

	public Leaves() {
		super();
	}




	public int getEmployeeId() {

		if(this.employee == null) {
			return this.intern.getId();
		}
		return this.employee.getId();

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfLeave() {
		return numberOfLeave;
	}

	public void setNumberOfLeave(int numberOfLeave) {
		this.numberOfLeave = numberOfLeave;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getLeavetype() {
		return leavetype;
	}

	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getGrantedOption() {
		return grantedOption;
	}

	public void setGrantedOption(String grantedOption) {
		this.grantedOption = grantedOption;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUsername() {
		if(this.employee == null) {
			return this.intern.getUser().getUsername();
		}
		return this.employee.getUser().getUsername();
	}




	

	


}
