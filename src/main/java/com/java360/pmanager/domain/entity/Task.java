package com.java360.pmanager.domain.entity;

import com.java360.pmanager.domain.model.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name= "id", nullable = false, length = 36)
    private String id;

    @Column(name= "title", nullable = false, length = 80)
    private String title;

    @Column(name= "description", nullable = false, length = 150)
    private String description;

    @Column(name= "member_of_days", nullable = false)
    private Integer numberOfDays;

    @Column(name= "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}
