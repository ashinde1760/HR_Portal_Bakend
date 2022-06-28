package com.project.service;

import org.springframework.web.multipart.MultipartFile;


import com.project.entity.Documentation;

public interface DocsService {
	
	//store attachement
		public Documentation UploadDocument(MultipartFile file,Documentation comDoc,int id);

		public Documentation UploadDocumenToIntern(MultipartFile file,Documentation comDoc,int id);

}
