package com.java360.pmanager.domain.infrastructure.dto;

import com.java360.pmanager.domain.model.ProjectStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class SaveProjectDataDTO {

    @NotNull(message = "Name cannot be empty")
    @Size(min = 1, max = 80, message = "Invalid name")
    private final String name;

    @NotNull(message = "Description cannot be empty")
    @Size(min = 1, max = 150 ,message = "Invalid description")
    private final String description;

    @NotNull(message = "Initial date cannot be empty")
    private final LocalDate initialDate;

    @NotNull(message = "Final date cannot be empty")
    private final LocalDate finalDate;

    private final String status;

    @AssertTrue(message = "Dates are not consistent")
    @SuppressWarnings("unused")
    private boolean isInitialDateBeforeFinalDate(){

        return initialDate.isBefore(finalDate);
    }
    // Returns false if initialDate is not before finalDate
   // Example: initialDate = "2025-02-01", finalDate = "2025-01-01"


}

