package com.project.service;

import org.springframework.web.multipart.MultipartFile;


import com.project.entity.DetailsDoc;

public interface DetailsDocService {
	
	//store attachement
	public DetailsDoc UploadDocument(MultipartFile file,DetailsDoc detailsDoc,int id);

}
