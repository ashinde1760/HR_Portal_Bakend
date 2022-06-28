package com.project.entity;


import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity

public class Employee {

	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE)
	  private int id;
		private String name;
		private String location;

		private String designation;
		private String reportingTo;
		private String mobile;

		private String email;

		private String department;
		
		private String permanentAddress;
		
		private String currentAddress;

		@DateTimeFormat(pattern = "dd/mm/yyyy")
		private Date DOJ;
		@DateTimeFormat(pattern = "dd/mm/yyyy")
		private Date DOL;

		private String review;

		private String actionTaken;
		
		@Lob
		@Column(columnDefinition = "MEDIUMBLOB")
		private String profilePic;

		@OneToOne(mappedBy = "employee",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
		@JsonBackReference
		private User user;


		 @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,orphanRemoval = true)
			@JsonManagedReference(value = "employee-leaves")
		 @Fetch(value = FetchMode.SUBSELECT)
		 private List<Leaves> leaveList = new ArrayList<>();

		@OneToMany(cascade = CascadeType.ALL,mappedBy = "employee",fetch = FetchType.EAGER, orphanRemoval = true)
		@JsonManagedReference(value = "employee-docs")
		private List<Documentation> documents;

		
		@OneToMany(cascade = CascadeType.ALL,mappedBy = "employee",fetch = FetchType.EAGER, orphanRemoval = true)
		@JsonManagedReference(value = "employee-bills")
		 @Fetch(value = FetchMode.SUBSELECT)
		private List<Bills> bills;

		
		
		@JsonManagedReference(value = "employee-report")
		@OneToMany( mappedBy = "employee",cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
		@Fetch(value = FetchMode.SUBSELECT)
		private List<Report> reportList = new ArrayList<>();

		@OneToOne(mappedBy = "employee",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
		@JsonManagedReference(value = "employee-comDocs")
		private ComDocument comDocument;
        
        
         private int totalEarnedLeaves;
		
		private int totalCasualLeaves;
		
		private int totalSickLeaves;
		
		@OneToOne(mappedBy = "employee",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
		@JsonManagedReference(value = "employee-deatilsDoc")
		private DetailsDoc detailsDoc;

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
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

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}




		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public Date getDOJ() {
			return DOJ;
		}

		public void setDOJ(Date doj) {
			DOJ = doj;
		}

		public Date getDOL() {
			return DOL;
		}

		public void setDOL(Date dOL) {
			DOL = dOL;
		}

	

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}






		public List<Bills> getBills() {
			return bills;
		}

		public void setBills(List<Bills> bills) {
			this.bills = bills;
		}

		public String getPermanentAddress() {
			return permanentAddress;
		}

		public void setPermanentAddress(String permanentAddress) {
			this.permanentAddress = permanentAddress;
		}

		public String getCurrentAddress() {
			return currentAddress;
		}

		public void setCurrentAddress(String currentAddress) {
			this.currentAddress = currentAddress;
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

		public List<Leaves> getLeavelist() {
			return leaveList;
		}

		public void setLeavelist(List<Leaves> leavelist) {
			this.leaveList = leavelist;
		}

		public Employee() {
			super();
		}


		public String getUsername() {
			if(this.user == null)
			{
				return "username Not Found!!";
			}
			return this.user.getUsername();
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
       
       
       public String getDetailsFilename() {
    	   if(this.detailsDoc ==null) {
    		   return "file not present!!";
    	   }
    	   return this.detailsDoc.getFilename();
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

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
    
	public int getTotalCasualLeaves() {
		return totalCasualLeaves;
	}

	public void setTotalCasualLeaves(int totalCasualLeaves) {
		this.totalCasualLeaves = totalCasualLeaves;
	}

	public int getTotalEarnedLeaves() {
		return totalEarnedLeaves;
	}

	public void setTotalEarnedLeaves(int totalEarnedLeaves) {
		this.totalEarnedLeaves = totalEarnedLeaves;
	}

	public int getTotalSickLeaves() {
		return totalSickLeaves;
	}

	public void setTotalSickLeaves(int totalSickLeaves) {
		this.totalSickLeaves = totalSickLeaves;
	}

	public DetailsDoc getDetailsDoc() {
		return detailsDoc;
	}

	public void setDetailsDoc(DetailsDoc detailsDoc) {
		this.detailsDoc = detailsDoc;
	}

	public String getReportingTo() {
		return reportingTo;
	}

	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
	}






    
	
  
}
