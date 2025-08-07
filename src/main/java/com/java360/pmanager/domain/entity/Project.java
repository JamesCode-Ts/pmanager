package com.java360.pmanager.domain.entity;

import com.java360.pmanager.domain.model.ProjectStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "name", nullable = false, length = 36)
    private String name;

    @Column(name = "description", nullable = false, length = 150)
    private String description;

    @Column(name = "initial_date", nullable = false)
    private LocalDate initialDate;

    @Column(name = "final_date", nullable = false)
    private LocalDate finalDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @ManyToMany // Defines a many-to-many relationship
    @JoinTable(
            name = "project_member", // Join table name
            joinColumns = @JoinColumn(name = "project_id"), // FK referencing Project (owner)
            inverseJoinColumns = @JoinColumn(name = "member_id") // FK referencing Member (inverse)
    )
    private List<Member> members; // Members linked to this project


    @OneToMany(mappedBy = "project")
    private List<Task> tasks;
}


