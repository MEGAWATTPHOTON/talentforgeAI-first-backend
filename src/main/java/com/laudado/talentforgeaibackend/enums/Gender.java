package com.laudado.talentforgeaibackend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Mirrors the frontend's Gender type exactly:
 *
 *   // types/auth.types.ts
 *   export type Gender = "male" | "female" | "other" | "prefer_not_to_say";
 *
 * Wire values (JSON) are the lowercase snake_case strings the frontend
 * sends/expects (e.g. in RegisterJobSeekerRequest.gender and the
 * GENDER_OPTIONS select in utils/industries.ts) — NOT the Java constant
 * names. @JsonValue / @JsonCreator keep serialization aligned to those
 * exact strings regardless of the enum constant names below.
 */
public enum Gender {
    MALE("male"),
    FEMALE("female"),
    OTHER("other"),
    PREFER_NOT_TO_SAY("prefer_not_to_say");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Gender fromValue(String value) {
        for (Gender gender : values()) {
            if (gender.value.equalsIgnoreCase(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unknown gender value: " + value);
    }

    @Override
    public String toString() {
        return value;
    }
}
