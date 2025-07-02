package com.java360.pmanager.domain.exception;

import com.java360.pmanager.domain.infrastructure.dto.exception.RequestException;

public class ProjectNotFoundExeption  extends RequestException {

 public ProjectNotFoundExeption(String projectId){
     super("ProjectNotFound,", "Project not fond: " + projectId);


 }

}
