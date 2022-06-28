package com.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.ComDocument;

public interface ComDocumentRepository extends JpaRepository<ComDocument, Integer> {

	public ComDocument findByFilename(String filename);

}
