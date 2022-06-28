package com.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.project.entity.DetailsDoc;

public interface DetailsDocRepository extends JpaRepository<DetailsDoc, Integer> {
	
	public DetailsDoc findByFilename(String filename);

}
