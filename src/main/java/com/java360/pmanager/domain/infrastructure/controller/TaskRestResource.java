package com.java360.pmanager.domain.infrastructure.controller;

import com.java360.pmanager.domain.applicationservice.ProjectService;
import com.java360.pmanager.domain.applicationservice.TaskService;
import com.java360.pmanager.domain.entity.Project;
import com.java360.pmanager.domain.entity.Task;
import com.java360.pmanager.domain.infrastructure.dto.ProjectDTO;
import com.java360.pmanager.domain.infrastructure.dto.SaveProjectDataDTO;
import com.java360.pmanager.domain.infrastructure.dto.SaveTaskDataDTO;
import com.java360.pmanager.domain.infrastructure.dto.TaskDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.java360.pmanager.domain.infrastructure.controller.RestConstants.PATH_PROJECTS;
import static com.java360.pmanager.domain.infrastructure.controller.RestConstants.PATH_TASKS;

@RestController
@RequestMapping(PATH_TASKS)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class TaskRestResource {

    private final TaskService taskService;

    // Estudar esta estrutura no chatgpt
    @PostMapping
    public ResponseEntity<TaskDTO> creatProject(@RequestBody @Valid SaveTaskDataDTO saveTaskData) {
        Task task = taskService.createTask(saveTaskData);

        return ResponseEntity
                .created(URI.create(PATH_TASKS + task.getId()))
                .body(TaskDTO.create(task));

    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> load(@PathVariable("id") String taskId){
            Task task = taskService.loadTask(taskId);
            return ResponseEntity.ok(TaskDTO.create(task));

    }

}




