package com.project.service.impl;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



import com.project.entity.Template;
import com.project.repo.TemplateRepository;
import com.project.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {
	
	@Autowired
	private TemplateRepository templateRepository;

	@Override
	public Template UploadTemplate(MultipartFile file, Template template) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
 	     try {
 	    	 
 	    	
		template.setFile(file.getBytes());
		  template.setFilename(fileName);
		  String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/files/download/")
					.path(fileName)
					.toUriString();
		  template.setFileUri(fileDownloadUri);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


  
	   return this.templateRepository.save(template);
	    

	}

	}






