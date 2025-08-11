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
import org.apache.catalina.LifecycleState;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<TaskDTO> load(@PathVariable("id") String taskId) {
        Task task = taskService.loadTask(taskId);
        return ResponseEntity.ok(TaskDTO.create(task));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedTask(@PathVariable("id") String taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(
            @PathVariable("id") String taskId,
            @RequestBody @Valid SaveTaskDataDTO saveTaskData
    ) {
        Task task = taskService.updateTask(taskId, saveTaskData);
        return ResponseEntity.ok(TaskDTO.create(task));
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findTasks(
            @RequestParam(value = "projectId", required = false) String projectId,
            @RequestParam(value = "memberId", required = false) String memberId,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "partialTitle", required = false) String partialTitle
    ) {
        List<Task> tasks = taskService.findTasks(projectId, memberId, status, partialTitle);
        return ResponseEntity.ok(tasks.stream().map(TaskDTO::create).toList());
    }
}




