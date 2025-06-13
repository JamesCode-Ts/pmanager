package com.java360.pmanager.domain.entity;

import com.java360.pmanager.domain.model.ProjectStatus;

import java.time.LocalDate;
import java.util.Objects;

public class Project {


    private String id;

    private String name;

    private String description;

    private LocalDate initialDate;

    private LocalDate finalDate;

    private ProjectStatus status;

    public Project(String id, String name, String description, LocalDate initialDate, LocalDate finalDate, ProjectStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.status = status;
    }

    public Project(){


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(name, project.name) && Objects.equals(description, project.description) && Objects.equals(initialDate, project.initialDate) && Objects.equals(finalDate, project.finalDate) && status == project.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, initialDate, finalDate, status);
    }
}


