package com.project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.entity.Intern;


public interface InternService {


		//get employee by email
		public Intern getIntern(int id);

		//load all employee

		public List<Intern> getAllIntern();

		//delete employee
		public void deleteIntern(int id);

		//update Intern
		public Intern updateIntern(Intern intern);

       

		  //uploading profile pic to intern
			public String UploadProfilePic(MultipartFile file, int id);

}
