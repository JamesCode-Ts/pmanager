package com.java360.pmanager.domain.applicationservice;

import com.java360.pmanager.domain.entity.Task;
import com.java360.pmanager.domain.exception.TaskNotFoundExeption;
import com.java360.pmanager.domain.infrastructure.dto.SaveTaskDataDTO;
import com.java360.pmanager.domain.model.TaskStatus;
import com.java360.pmanager.domain.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public Task createTask(SaveTaskDataDTO saveTaskData){
        Task task = Task
                .builder()
                .title(saveTaskData.getTitle())
                .description(saveTaskData.getDescription())
                .numberOfDays(saveTaskData.getNumberOfDays())
                .status(TaskStatus.PENDING)
                .build();

        taskRepository.save(task);
        return task;
    }

    public Task loadTask(String taskId){
        return taskRepository
                .findById(taskId)
                .orElseThrow(() -> new TaskNotFoundExeption(taskId));
    }

    @Transactional
    public void deleteTask(String taskId){
        Task task = loadTask(taskId);
        taskRepository.delete(task);
    }

}
