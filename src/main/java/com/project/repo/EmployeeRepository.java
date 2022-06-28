package com.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public Employee findByEmail(String email);



}
