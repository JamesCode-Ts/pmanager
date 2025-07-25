package com.java360.pmanager.domain.applicationservice;

import com.java360.pmanager.domain.entity.Project;
import com.java360.pmanager.domain.exception.ProjectNotFoundExeption;
import com.java360.pmanager.domain.infrastructure.dto.SaveProjectDataDTO;
import com.java360.pmanager.domain.infrastructure.exception.DuplicateProjectException;
import com.java360.pmanager.domain.infrastructure.exception.InvalidProjectStatusException;
import com.java360.pmanager.domain.infrastructure.exception.RequestException;
import com.java360.pmanager.domain.model.ProjectStatus;
import com.java360.pmanager.domain.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public Project createProject(SaveProjectDataDTO saveProjectData) {
        if(existsProjectWithName(saveProjectData.getName(), null)){
            throw new DuplicateProjectException(saveProjectData.getName());

        }

        Project project = Project
                .builder()
                .name(saveProjectData.getName())
                .description(saveProjectData.getDescription())
                .initialDate(saveProjectData.getInitialDate())
                .finalDate(saveProjectData.getFinalDate())
                .status(ProjectStatus.PENDING)
                .build();

        projectRepository.save(project);

        log.info("Project created: {}", project);
        return project;

    }
    // Returns the project found by projectId or throws an exception if not found
    public Project loadProject(String projectId) {
       return projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundExeption(projectId));

    }

    @Transactional
    public void deleteProject(String projectId){
        Project project = loadProject(projectId);
        projectRepository.delete(project);
    }

    @Transactional
    public Project updateProject(String projectId, SaveProjectDataDTO saveProjectDate){
        if(existsProjectWithName(saveProjectDate.getName(), projectId)){
            throw new DuplicateProjectException(saveProjectDate.getName());

        }
        Project project = loadProject(projectId);

        project.setName(saveProjectDate.getName());
        project.setDescription(saveProjectDate.getDescription());
        project.setInitialDate(saveProjectDate.getInitialDate());
        project.setInitialDate(saveProjectDate.getFinalDate());
        project.setStatus(convertToProjectStatus(saveProjectDate.getStatus()));

        return project;

    }
    private ProjectStatus convertToProjectStatus(String statusStr){

        try {
            return ProjectStatus.valueOf(statusStr);
        } catch (IllegalArgumentException | NullPointerException e){

            throw new InvalidProjectStatusException(statusStr);

        }

    }
    /**
     * Checks if the given name is already used by another project in the database.
     * <p>
     * This method is typically used when updating a project: it ensures that no
     * other project (with a different ID) already has the same name. The current
     * project itself is ignored in the check, since having the same name on itself
     * is allowed.
     *
     * @param name the project name to check
     * @param idToExclude the ID of the project currently being edited;
     *                     this ID will be ignored during the check
     * @return true if another project with a different ID and the same name exists; false otherwise
     */
    private boolean existsProjectWithName(String name, String idToExclude){
        // Query for a project with the given name(Consulta um projeto no banco com o nome fornecido)
        return projectRepository
              .findByName(name)
                // If found, check if its ID is different from the one being edited
                // - If IDs are equal: it's the same project, so no conflict
                // - If IDs are different: another project already has this name (conflict)
                .filter(p -> !Objects.equals(p.getId(), idToExclude))

                // If present, it means a conflicting project was found
                .isPresent();

    }
}
