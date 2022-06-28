package com.project.service;

import java.util.List;

import com.project.entity.Leaves;


public interface LeaveService {

public Leaves addLeaveToEmployee(int userId, Leaves leave) throws Exception;

public Leaves addLeaveToIntern(int id,Leaves leave) throws Exception;

	public Leaves updateLeave(  Leaves leave);

	public List<Leaves> getLeaves();

	public Leaves getLeave(int Id);

	public void deleteLeaves(int id);

	//updating leave in Employee
//
//	public Leaves upadteLeaveForEmployee(int id , Leaves leave) throws Exception;

	//updating leave in Intern




}
