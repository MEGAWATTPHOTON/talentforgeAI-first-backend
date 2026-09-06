package com.laudado.talentforgeaibackend.dto.responseDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {
    private boolean success=false;
    private String message;
    private Map<String, List<String>> errors;
    private Integer statusCode;
}
