package com.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.Bills;

public interface BillsRepository extends JpaRepository<Bills,Integer>{

	public Bills findByFilename(String filename);

}
