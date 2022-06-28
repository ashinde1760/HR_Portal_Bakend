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

import com.project.entity.DetailsDoc;
import com.project.repo.DetailsDocRepository;
import com.project.service.DetailsDocService;

@RestController
@CrossOrigin("*")
@RequestMapping("/details")
public class DetailsDocController {
	
	@Autowired
  private 	DetailsDocService detailsService;
	
	@Autowired
   private	 DetailsDocRepository docsRepository;
	
	 @PostMapping( value = "/upload/{id}")
	   public ResponseEntity<String> uploadFile( @RequestParam("file") MultipartFile file,@PathVariable("id") int id) {
	      
		   DetailsDoc doc = new DetailsDoc();
	          this.detailsService.UploadDocument(file, doc, id);

			return ResponseEntity.ok("file uploaded");
		  }
	 
	 
	  @GetMapping("/download/{fileName:.+}")
	   public ResponseEntity<Resource> downloadFromDB(@PathVariable String fileName) throws Exception {
	   	DetailsDoc document = this.docsRepository.findByFilename(fileName);
	   	System.out.println(document.getFileUri());

	    return ResponseEntity.ok().
//	    		contentType(new MediaType("text")).
	            header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + document.getFilename() + "\"").
	            body(new ByteArrayResource(document.getFile()));

	}

}
