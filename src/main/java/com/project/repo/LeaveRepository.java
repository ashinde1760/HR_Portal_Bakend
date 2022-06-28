package com.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.Leaves;

public interface LeaveRepository extends JpaRepository<Leaves, Integer> {


}
