package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Project;
import com.project.repo.ProjectRepository;
import com.project.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectrepository;

	@Override
	public Project addProject(Project project) {

		return this.projectrepository.save(project);
	}

	@Override
	public Project updateProject(Project project) {

		return this.projectrepository.save(project);
	}

	@Override
	public List<Project> getProjects() {
		// TODO Auto-generated method stub
		return this.projectrepository.findAll();
	}

	@Override
	public Project getProject(int Id) {
		// TODO Auto-generated method stub
		return this.projectrepository.findById(Id).get();
	}

	@Override
	public void deleteProject(int id) {
		Project project = new Project();
		project.setId(id);
		this.projectrepository.delete(project);

	}

}
