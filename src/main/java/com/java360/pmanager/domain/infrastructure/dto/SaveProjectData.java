package com.java360.pmanager.domain.infrastructure.dto;

import com.java360.pmanager.domain.model.ProjectStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SaveProjectData {

    private final String name;
    private final String description;
    private final LocalDate initialDate;
    private final LocalDate finalDate;
    private final String status;
}
