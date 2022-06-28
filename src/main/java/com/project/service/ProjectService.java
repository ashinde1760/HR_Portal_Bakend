package com.project.service;

import java.util.List;

import com.project.entity.Project;

public interface ProjectService {

	public Project addProject(Project project);

	public Project updateProject(Project project);

	public List<Project> getProjects();

	public Project getProject(int Id);

	public void deleteProject(int id);



}
