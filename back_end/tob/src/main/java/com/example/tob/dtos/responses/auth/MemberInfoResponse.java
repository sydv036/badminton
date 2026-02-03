package com.example.tob.dtos.responses.auth;

public interface MemberInfoResponse {

    Long getSystemId();

    String getPublicId();

    String getEmail();

    String getPhoneNumber();

    String getPassword();

    Boolean getLocked();

    Boolean getActived();

}
