package com.project.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ComDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;


	@Lob
	private byte[] file;

	private String filename;

	private String fileUri;

	@OneToOne(fetch = FetchType.EAGER)
	@JsonBackReference(value = "employee-comDocs")
	private Employee employee;


	@OneToOne(fetch = FetchType.EAGER)
	@JsonBackReference(value = "intern-comDocs")
	private Intern intern;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
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

	public ComDocument() {
		super();
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








}
