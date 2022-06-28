package com.project.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.project.entity.Documentation;
import com.project.entity.Employee;
import com.project.entity.Intern;
import com.project.repo.DocsRepository;
import com.project.repo.EmployeeRepository;
import com.project.repo.InternRepository;
import com.project.service.DocsService;

@Service
public class DocsServiceImpl implements DocsService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DocsRepository docsRepository;
	
	@Autowired
	private InternRepository internRepository;

	@Override
	public Documentation UploadDocument(MultipartFile file, Documentation Doc, int id) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
 	     try {
		Doc.setFile(file.getBytes());
		  Doc.setFilename(fileName);
		  String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/files/download/")
					.path(fileName)
					.toUriString();
		  Doc.setFileUri(fileDownloadUri);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

      Employee employee1 = new Employee();
 	 List<Documentation> docsList = new ArrayList<>();

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
      Doc.setEmployee(employee);


      //tie leave to employee
	   Documentation Doc1 = this.docsRepository.save(Doc);
	   
	   docsList.add(Doc1);
      employee1.setDocuments(docsList);


      return Doc1;
	}

	@Override
	public Documentation UploadDocumenToIntern(MultipartFile file, Documentation Doc, int id) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	     try {
		Doc.setFile(file.getBytes());
		  Doc.setFilename(fileName);
		  String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/files/download/")
					.path(fileName)
					.toUriString();
		  Doc.setFileUri(fileDownloadUri);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

     Intern intern1 = new Intern();
	 List<Documentation> docsList = new ArrayList<>();

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

     //tie intern to leave
     Doc.setIntern(intern);


     //tie leave to intern
	   Documentation Doc1 = this.docsRepository.save(Doc);
	   
	   docsList.add(Doc1);
     intern1.setDocuments(docsList);


     return Doc1;
	
		
	}

}
