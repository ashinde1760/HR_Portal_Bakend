package com.project.service.impl;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.entity.Employee;
import com.project.entity.Intern;
import com.project.repo.InternRepository;
import com.project.service.InternService;
@Service
public class InternServiceImpl implements InternService {

@Autowired
private InternRepository repository;



	@Override
	public Intern getIntern(int id) {

		return this.repository.findById(id).get();
	}

	@Override
	public List<Intern> getAllIntern() {

		return this.repository.findAll();
	}

	@Override
	public void deleteIntern(int id) {
	 this.repository.deleteById(id);

	}

	@Override
	public Intern updateIntern(Intern intern) {

		return this.repository.save(intern);
	}

	@Override
	public String UploadProfilePic(MultipartFile file, int id) {
		Optional<Intern> OptionalIntern = this.repository.findById(id);
        if(!OptionalIntern.isPresent()) {
       	 new Exception("Employee with id not present");
        }
        
        Intern intern1 = OptionalIntern.get();

        try {
        	if(intern1.getProfilePic() !=null) {
        		intern1.setProfilePic(null);
        	}
        		
			intern1.setProfilePic(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
        this.repository.save(intern1);
        
        return "file uploaded!!!!";

	}




}
