package com.java360.pmanager.domain.infrastructure.exception;

public class InvalidProjectStatusException  extends RequestException {

    public InvalidProjectStatusException(String statusStr){
        super("InvalidProjectStatus", "Invalid project status " + statusStr);
    }
}
