package com.laudado.talentforgeaibackend.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    JOB_SEEKER("job_seeker"),
    RECRUITER("recruiter"),
    ADMIN("admin"),
    SUPER_ADMIN("super_admin");

    private final String value;
    UserRole(String value){
        this.value=value;
    }
    @JsonValue
    public String getValue(){
        return value;
    }
}
