package com.laudado.talentforgeaibackend.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ThemeMode {
    LIGHT("light"),
    DARK("dark"),
    SYSTEM("system");

    private final String value;

    ThemeMode(String value){
        this.value=value;
    }
    @JsonValue
    public String getValue(){
        return value;
    }
}
