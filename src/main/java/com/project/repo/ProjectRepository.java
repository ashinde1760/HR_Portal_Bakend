package com.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.Project;

public interface ProjectRepository extends JpaRepository<Project,Integer> {

}
