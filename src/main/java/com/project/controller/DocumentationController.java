package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.project.entity.Documentation;
import com.project.repo.DocsRepository;
import com.project.service.DocsService;

@RestController
@RequestMapping("/documentation")
@CrossOrigin("*")
public class DocumentationController {
	
	@Autowired
	private DocsService docsService;
	@Autowired
	private DocsRepository docsRepository;
	
	

	   @PostMapping( value = "/upload/{id}")
	   public ResponseEntity<String> uploadFile( @RequestParam("file") MultipartFile file,@PathVariable("id") int id) {
	        Documentation Doc = new Documentation();
		 

	          this.docsService.UploadDocument(file, Doc, id);

			return ResponseEntity.ok("file uploaded");
		  }
	   
	   @PostMapping( value = "/upload/intern/{id}")
	   public ResponseEntity<String> uploadFiletoIntern( @RequestParam("file") MultipartFile file,@PathVariable("id") int id) {
	        Documentation Doc = new Documentation();
		

	          this.docsService.UploadDocumenToIntern(file, Doc, id);

			return ResponseEntity.ok("file uploaded");
		  }
	   
	   

	   @GetMapping("/download/{fileName:.+}")
	   public ResponseEntity<Resource> downloadFromDB(@PathVariable String fileName) throws Exception {
	           Documentation document = this.docsRepository.findByFilename(fileName);
	   	System.out.println(document.getFileUri());

	    return ResponseEntity.ok().
//	    		contentType(new MediaType("text")).
	            header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + document.getFilename() + "\"").
	            body(new ByteArrayResource(document.getFile()));

	}

}
