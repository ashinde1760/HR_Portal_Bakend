package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Bills {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int billsId;
	

	private float amount;
	private String reason;
	
	private String nature;
	private String approveOption;

	@Lob
	private byte[] attachement;

	private String filename;

	private String fileUri;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference(value = "employee-bills")
	private Employee employee;


	public Bills() {
		super();
	}

	

	public int getEmployeeId() {

		
		return this.employee.getId();

	}
	public int getBillsId() {
		return billsId;
	}

	public void setBillsId(int billsId) {
		this.billsId = billsId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public byte[] getAttachement() {
		return attachement;
	}

	public void setAttachement(byte[] attachement) {
		this.attachement = attachement;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}



	public String getFileUri() {
		return fileUri;
	}

	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}



	public Bills(int billsId, float amount, String reason, String approveOption, byte[] attachement, String filename,
			String fileUri) {
		super();
		this.billsId = billsId;
		this.amount = amount;
		this.reason = reason;
		this.approveOption = approveOption;
		this.attachement = attachement;
		this.filename = filename;
		this.fileUri = fileUri;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApproveOption() {
		return approveOption;
	}

	public void setApproveOption(String approveOption) {
		this.approveOption = approveOption;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}



}
