package com.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.project.entity.Documentation;

public interface DocsRepository extends JpaRepository<Documentation, Integer>{
	
	public Documentation  findByFilename(String filename);

}
