package com.project.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.entity.ComDocument;
import com.project.entity.Employee;
import com.project.entity.Intern;
import com.project.repo.ComDocumentRepository;
import com.project.repo.EmployeeRepository;
import com.project.repo.InternRepository;
import com.project.service.ComDocumentService;

@Service
public class ComDocumentServiceImpl implements ComDocumentService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ComDocumentRepository documentRepository;
	@Autowired
	private InternRepository internRepository;

	@Override
	public ComDocument UploadDocument(MultipartFile file, ComDocument comDoc,int id) {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
  	     try {
  	    	 
  	    	
		comDoc.setFile(file.getBytes());
		  comDoc.setFilename(fileName);
		  String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/files/download/")
					.path(fileName)
					.toUriString();
		  comDoc.setFileUri(fileDownloadUri);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


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

       if(employee.getComDocument() != null)
       {
    
    	 	  ComDocument doc1 = employee.getComDocument();
    	 	   doc1.setEmployee(employee);
    	   	  doc1.setFile(comDoc.getFile());
    	   	  doc1.setFilename(comDoc.getFilename());
    	   	  doc1.setFileUri(comDoc.getFileUri());
    	   	  //tie Employee to leave
    	   
    	       
    	      //tie leave to employee
    		   return this.documentRepository.save(doc1);

       }

       
       //tie Employee to leave
       comDoc.setEmployee(employee);
     
       //tie leave to employee
	   return this.documentRepository.save(comDoc);
	    

	}

	@Override
	public ComDocument UploadDocumenToIntern(MultipartFile file, ComDocument comDoc, int id) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
 	     try {
		comDoc.setFile(file.getBytes());
		  comDoc.setFilename(fileName);
		  String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/files/download/")
					.path(fileName)
					.toUriString();
		  comDoc.setFileUri(fileDownloadUri);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

      Intern intern1 = new Intern();

      Optional<Intern> byId = this.internRepository.findById(id);
      try {
			if (!byId.isPresent()) {
			    throw new Exception("Employee with id " + id + " does not exist");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      Intern intern = byId.get();


      if(intern.getComDocument() != null)
      {
      ComDocument doc1 = intern.getComDocument();
   	  doc1.setFile(comDoc.getFile());
   	  doc1.setFilename(comDoc.getFilename());
   	  doc1.setFileUri(comDoc.getFileUri());
   	  //tie Employee to leave
      doc1.setIntern(intern);
    
      //tie leave to employee
	   return this.documentRepository.save(doc1);

      }

      
      //tie Employee to leave
      comDoc.setIntern(intern);
    
      //tie leave to employee
	   return this.documentRepository.save(comDoc);
	}

}
