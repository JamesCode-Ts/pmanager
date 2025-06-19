package com.java360.pmanager.domain.infrastructure.dto.controller;

import com.java360.pmanager.domain.applicationservice.ProjectService;
import com.java360.pmanager.domain.entity.Project;
import com.java360.pmanager.domain.infrastructure.dto.SaveProjectDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectRestResource {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> creatProject(@RequestBody SaveProjectDataDTO saveProjectData) {
        Project project = projectService.createProject(saveProjectData);

       return ResponseEntity
               .created(URI.create("/projects/" + project.getId()))
               .body(project);

    }
}
