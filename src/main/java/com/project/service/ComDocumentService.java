package com.project.service;

import org.springframework.web.multipart.MultipartFile;

import com.project.entity.ComDocument;


public interface ComDocumentService {

	//store attachement
	public ComDocument UploadDocument(MultipartFile file,ComDocument comDoc,int id);

	public ComDocument UploadDocumenToIntern(MultipartFile file,ComDocument comDoc,int id);

}
