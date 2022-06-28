package com.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.project.entity.Template;

public interface TemplateRepository  extends JpaRepository<Template, Integer>{
	
	public Template findByFilename(String filename);

}