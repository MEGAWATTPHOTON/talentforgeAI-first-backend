package com.laudado.talentforgeaibackend.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CompanySize {
    SIZE_1_10("1-10"),
    SIZE_11_50("11-50"),
    SIZE_51_200("51-200"),
    SIZE_201_500("201-500"),
    SIZE_501_1000("501-1000"),
    SIZE_1000_PLUS("1000+");

    private final String value;

    CompanySize(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    public static CompanySize fromEmployeeCount(long employeeCount) {
        if (employeeCount <= 10) {
            return SIZE_1_10;
        } else if (employeeCount <= 50) {
            return SIZE_11_50;
        } else if (employeeCount <= 200) {
            return SIZE_51_200;
        } else if (employeeCount <= 500) {
            return SIZE_201_500;
        } else if (employeeCount <= 1000) {
            return SIZE_501_1000;
        } else {
            return SIZE_1000_PLUS;
        }
    }
}