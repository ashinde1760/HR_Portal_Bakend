package com.project.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.entity.DetailsDoc;
import com.project.entity.Employee;
import com.project.repo.DetailsDocRepository;
import com.project.repo.EmployeeRepository;
import com.project.service.DetailsDocService;

@Service
public class DetailsDocsServiceImpl implements DetailsDocService {
	
	@Autowired
	private DetailsDocRepository detailsRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public DetailsDoc UploadDocument(MultipartFile file, DetailsDoc doc, int id) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
 	     try {
 	    	 
 	    	
		doc.setFile(file.getBytes());
		  doc.setFilename(fileName);
		  String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/files/download/")
					.path(fileName)
					.toUriString();
		  doc.setFileUri(fileDownloadUri);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

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

      if(employee.getDetailsDoc() != null)
      {
   
   	 	  DetailsDoc doc1 = employee.getDetailsDoc();
   	   	  doc1.setFile(doc.getFile());
   	   	  doc1.setFilename(doc.getFilename());
   	   	  doc1.setFileUri(doc.getFileUri());
   	   	  //tie Employee to leave
   	      doc1.setEmployee(employee);
   	    
   	      //tie leave to employee
   		   return this.detailsRepository.save(doc1);

      }

      
      //tie Employee to leave
      doc.setEmployee(employee);
    
      //tie leave to employee
	   return this.detailsRepository.save(doc);
	    

	}

}
