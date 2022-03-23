package com.diwash.ManyToMany.controller;

import com.diwash.ManyToMany.Models.Project;
import com.diwash.ManyToMany.exception.ResourceNotFoundException;
import com.diwash.ManyToMany.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    // create Project REST API
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    // get Project by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable  long id){
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not exist with id:" + id));
        return ResponseEntity.ok(project);
    }

    // update project REST API
    @PutMapping("{id}")
    public ResponseEntity<Project> updateProject(@PathVariable long id,@RequestBody Project project) {
        Project updateProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("project not exist with id: " + id));

        updateProject.setProjectId(project.getProjectId());
        updateProject.setTitle(project.getTitle());

        projectRepository.save(updateProject);

        return ResponseEntity.ok(updateProject);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable long id){

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not exist with id: " + id));

        projectRepository.delete(project);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

