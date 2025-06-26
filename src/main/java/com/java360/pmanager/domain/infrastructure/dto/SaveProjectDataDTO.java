package com.java360.pmanager.domain.infrastructure.dto;

import com.java360.pmanager.domain.model.ProjectStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class SaveProjectDataDTO {

    @NotNull
    @Size(min = 1, max = 80)
    private final String name;

    @NotNull
    @Size(min = 1, max = 150)
    private final String description;

    @NotNull
    private final LocalDate initialDate;

    @NotNull
    private final LocalDate finalDate;

    private final String status;
}

