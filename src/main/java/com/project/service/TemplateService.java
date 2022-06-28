package com.project.service;

import org.springframework.web.multipart.MultipartFile;

import com.project.entity.Template;



public interface TemplateService {

	//store attachement
	public Template UploadTemplate(MultipartFile file,Template template);

//	public Template UploadDocumenToIntern(MultipartFile file,Template template);
}
