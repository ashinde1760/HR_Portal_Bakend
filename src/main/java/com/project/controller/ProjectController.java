package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Project;
import com.project.service.ProjectService;

@RestController
@RequestMapping("/project")
@CrossOrigin("*")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	//add project
	@PostMapping("/")
	public ResponseEntity<Project> addProject(@RequestBody Project project)
	{
		Project project1 = this.projectService.addProject(project);
		return ResponseEntity.ok(project1);
	}

	//get project
	@GetMapping("/{id}")
	public Project getProject(@PathVariable("id") int ProjectId)
	{
		return this.projectService.getProject(ProjectId);
	}

	//getallProjects
	@GetMapping("/getall")
	public ResponseEntity<?> getAllProjects()
	{
		return ResponseEntity.ok(this.projectService.getProjects());
	}

    //update

	@PutMapping("/")
	public Project updateProject(@RequestBody Project project)
	{
		return this.projectService.updateProject(project);
	}

	//delete project
	@DeleteMapping("/{id}")
	public void deleteProject(@PathVariable("id") int projectId)
	{
		this.projectService.deleteProject(projectId);
	}
}
