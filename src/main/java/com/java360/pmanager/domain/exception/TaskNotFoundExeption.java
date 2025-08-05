package com.java360.pmanager.domain.exception;

import com.java360.pmanager.domain.infrastructure.exception.RequestException;

public class TaskNotFoundExeption extends RequestException {

 public TaskNotFoundExeption(String taskId){
     super("TaskNotFound,", "Task not fond: " + taskId);


 }

}
