package com.java360.pmanager.domain.applicationservice;

import com.java360.pmanager.domain.entity.Project;
import com.java360.pmanager.domain.infrastructure.dto.SaveProjectData;
import com.java360.pmanager.domain.model.ProjectStatus;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    public Project createProject(SaveProjectData saveProjectData) {

        Project project = Project
                .builder()
                .name(saveProjectData.getName())
                .description(saveProjectData.getDescription())
                .initialDate(saveProjectData.getInitialDate())
                .finalDate(saveProjectData.getFinalDate())
                .status(ProjectStatus.PENDING)
                .build();

        return  project;

    }
}