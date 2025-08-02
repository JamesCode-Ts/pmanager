package com.java360.pmanager.domain.infrastructure.dto;

import com.java360.pmanager.domain.entity.Member;
import com.java360.pmanager.domain.entity.Project;
import com.java360.pmanager.domain.model.ProjectStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Data
public class ProjectDTO {
    //data output, is the response
    private final String id;
    private final String name;
    private final String description;
    private final LocalDate initialDate;
    private final LocalDate finalDate;
    private final ProjectStatus status;
    private final Set<String> membersIds;

    public static ProjectDTO create(Project project){
        return new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getInitialDate(),
                project.getFinalDate(),
                project.getStatus(),
                Optional
                        .ofNullable(project.getMembers()) // avoid NullPointerException
                        .orElse(List.of())        // if null, use an empty list
                        .stream()                 // create a Stream of members
                        .map(Member::getId)       // get only the IDs of each member
                        .collect(toSet())         // convert to a Set<String>
        );


    }
}
