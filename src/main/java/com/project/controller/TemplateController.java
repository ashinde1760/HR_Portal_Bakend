package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.entity.ComDocument;
import com.project.entity.Template;
import com.project.repo.TemplateRepository;
import com.project.service.TemplateService;

@RestController
@CrossOrigin("*")
@RequestMapping("/template")
public class TemplateController {
	
 @Autowired
 TemplateService templateService;
 
 @Autowired
 TemplateRepository templateRepository;
 
 @PostMapping( value = "/upload")
 public Template uploadFile( @RequestParam("file") MultipartFile file) {
         Template template = new Template();
	   if(!file.getContentType().equals("application/pdf") ) {
		   System.out.println("pdf file required!!");
		   throw new IllegalArgumentException("Incorrect file type, PDF required.");

	   }
	   




		return this.templateService.UploadTemplate(file, template);
	  }
 
 @GetMapping("/download/{fileName:.+}")
 public ResponseEntity<Resource> downloadFromDB(@PathVariable String fileName) throws Exception {
  Template template = this.templateRepository.findByFilename(fileName);
  
 	System.out.println(template.getFileUri());

  return ResponseEntity.ok().
//  		contentType(new MediaType("text")).
          header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + template.getFilename() + "\"").
          body(new ByteArrayResource(template.getFile()));

}
 
 @GetMapping("/load")
 public List<Template> getall(){
	 
	 return this.templateRepository.findAll();
	 
 }
 
 @DeleteMapping("/delete/{id}")
 public void deletefile(@PathVariable("id") int id) {
	this.templateRepository.deleteById(id);
 }

}
