package com.java360.pmanager.domain.infrastructure.exception;

public class DuplicateProjectException extends  RequestException{

    public DuplicateProjectException(String name){
        super("ProjectDuplicate", "A project with the name already exists:" + name);
    }
}
