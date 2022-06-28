package com.project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.entity.Bills;

public interface BillsService {

	public Bills addBills(Bills bills);

	public List<Bills> allBills() ;

	public Bills updateBills(Bills bills);

	public Bills getBill(int Id);

	public void deleteBills(int id);

	//store attachement
	public Bills storeBills(MultipartFile file,Bills bill, int id);


}
