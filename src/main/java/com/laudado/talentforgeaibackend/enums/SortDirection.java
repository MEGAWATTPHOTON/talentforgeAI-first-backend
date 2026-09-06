package com.laudado.talentforgeaibackend.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SortDirection {
    ASC("asc"),
    DESC("desc");

    private final String value;

    SortDirection(String value){
        this.value=value;
    }
    @JsonValue
    public String getValue(){
        return value;
    }
}
