package com.laudado.talentforgeaibackend.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationStage {
    APPLIED("applied"),
    SCREENING("screening"),
    INTERVIEW("interview"),
    OFFER("offer"),
    HIRED("hired"),
    REJECTED("rejected"),
    WITHDRAWN("withdrawn");
    private final String value;
    ApplicationStage(String value){
        this.value=value;
    }
    @JsonValue
    public String getValue(){
        return value;
    }
}
