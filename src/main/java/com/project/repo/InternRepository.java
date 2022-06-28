package com.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.Intern;

public interface InternRepository extends JpaRepository<Intern,Integer> {

	public Intern findByEmail(String email);

}
