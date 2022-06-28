package com.project.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Intern {

	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE)
	  private int id;

	private String name;

	private String location;

	private String mentor;

	private String mobile;

	private String email;

	private String projectname;

	private String DOJ;

	private String DOL;

	private String address;

	private String review;

	private String actionTaken;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String profilePic;


	@OneToOne(mappedBy = "intern",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JsonBackReference
	private User user;

	@OneToMany( mappedBy = "intern",cascade = CascadeType.ALL, orphanRemoval=true)
	@JsonManagedReference(value = "intern-leave")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Leaves> leaveList = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "intern",fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference(value = "intern-docs")
	private List<Documentation> documents;

	@JsonManagedReference(value = "intern-report")
	@OneToMany( mappedBy = "intern",cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval=true)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Report> reportList = new ArrayList<>();



	@OneToOne(mappedBy = "intern",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonManagedReference(value = "intern-comDocs")
	private ComDocument comDocument;

     
	private int totalLeave;



	public Intern() {
		super();
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public List<Leaves> getLeaveList() {
		return leaveList;
	}




	public void setLeaveList(List<Leaves> leaveList) {
		this.leaveList = leaveList;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getLocation() {
		return location;
	}




	public void setLocation(String location) {
		this.location = location;
	}




	public String getMentor() {
		return mentor;
	}




	public void setMentor(String mentor) {
		this.mentor = mentor;
	}




	public String getMobile() {
		return mobile;
	}




	public void setMobile(String mobile) {
		this.mobile = mobile;
	}





	@JsonInclude(JsonInclude.Include.NON_NULL)
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getProjectname() {
		return projectname;
	}




	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}




	public String getDOJ() {
		return DOJ;
	}




	public void setDOJ(String dOJ) {
		DOJ = dOJ;
	}




	public String getDOL() {
		return DOL;
	}




	public void setDOL(String dOL) {
		DOL = dOL;
	}






	public String getProfilePic() {
		return profilePic;
	}




	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}




	







	public List<Documentation> getDocuments() {
		return documents;
	}




	public void setDocuments(List<Documentation> documents) {
		this.documents = documents;
	}




	public List<Report> getReportList() {
		return reportList;
	}




	public void setReportList(List<Report> reportList) {
		this.reportList = reportList;
	}


	  @JsonIgnore
	    @JsonProperty("UserId")
	public Integer getUserId() {
		return this.user.getUserId();
	}




	public ComDocument getComDocument() {
		return comDocument;
	}




	public void setComDocument(ComDocument comDocument) {
		this.comDocument = comDocument;
	}


    public String getFileName() {
 	   if(this.comDocument ==null) {
 		   return "file not present!!";
 	   }
 	   return this.comDocument.getFilename();
    }




	public String getReview() {
		return review;
	}




	public void setReview(String review) {
		this.review = review;
	}




	public String getActionTaken() {
		return actionTaken;
	}




	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}

	public String getUsername() {
		if(this.user == null)
		{
			return "username Not Found!!";
		}
		return this.user.getUsername();
	}




	public int getTotalLeave() {
		return totalLeave;
	}




	public void setTotalLeave(int totalLeave) {
		this.totalLeave = totalLeave;
	}

	




}
