package com.laudado.talentforgeaibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectOption<T> {
    private String label;
    private T value;
    private boolean disabled;
    private String icon;
}
