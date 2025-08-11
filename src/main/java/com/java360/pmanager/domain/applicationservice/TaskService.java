package com.java360.pmanager.domain.applicationservice;

import com.java360.pmanager.domain.entity.Member;
import com.java360.pmanager.domain.entity.Project;
import com.java360.pmanager.domain.entity.Task;
import com.java360.pmanager.domain.exception.InvalidTaskStatusException;
import com.java360.pmanager.domain.exception.TaskNotFoundExeption;
import com.java360.pmanager.domain.infrastructure.dto.SaveTaskDataDTO;
import com.java360.pmanager.domain.model.TaskStatus;
import com.java360.pmanager.domain.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final ProjectService projectService;
    private final MemberService memberService;
    private final TaskRepository taskRepository;


    @Transactional
    public Task createTask(SaveTaskDataDTO saveTaskData) {
    Project project = getProjectIfPossible(saveTaskData.getProjectId());
    Member member = getMemberIfPossible(saveTaskData.getMemberId());

        Task task = Task
                .builder()
                .title(saveTaskData.getTitle())
                .description(saveTaskData.getDescription())
                .numberOfDays(saveTaskData.getNumberOfDays())
                .status(TaskStatus.PENDING)
                .project(project)
                .assignedMember(member)
                .build();

        taskRepository.save(task);
        return task;
    }

    public Task loadTask(String taskId) {
        return taskRepository
                .findById(taskId)
                .orElseThrow(() -> new TaskNotFoundExeption(taskId));
    }

    @Transactional
    public void deleteTask(String taskId) {
        Task task = loadTask(taskId);
        taskRepository.delete(task);
    }


    @Transactional
    public Task updateTask(String taskId, SaveTaskDataDTO saveTaskData){

        Project project = getProjectIfPossible(saveTaskData.getProjectId());
        Member member = getMemberIfPossible(saveTaskData.getMemberId());

        Task task = loadTask(taskId);

        task.setTitle(saveTaskData.getTitle());
        task.setDescription(saveTaskData.getDescription());
        task.setNumberOfDays(saveTaskData.getNumberOfDays());
        task.setStatus(convertToTaskStatus(saveTaskData.getStatus()));

        return task;
    }

   private TaskStatus convertToTaskStatus(String statusStr){
        try {
            return TaskStatus.valueOf(statusStr); //convert for enum
        }catch (IllegalArgumentException | NullPointerException e){
            throw new InvalidTaskStatusException(statusStr);

        }
   }

    private Member getMemberIfPossible(String memberId) {

        Member member = null;
        if (!Objects.isNull(memberId)) {
            member = memberService.loadMemberbyId(memberId);
        }
        return member;
    }

    private Project getProjectIfPossible(String projectId){

        Project project = null;
        if(!Objects.isNull(projectId)){
            project = projectService.loadProject(projectId);
        }
        return project;
    }


}
