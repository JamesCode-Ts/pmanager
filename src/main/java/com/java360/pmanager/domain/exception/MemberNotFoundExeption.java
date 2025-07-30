package com.java360.pmanager.domain.exception;

import com.java360.pmanager.domain.infrastructure.exception.RequestException;

public class MemberNotFoundExeption extends RequestException {

 public MemberNotFoundExeption(String memberId){
     super("MemberNotFound,", "Member not fond: " + memberId);


 }

}
