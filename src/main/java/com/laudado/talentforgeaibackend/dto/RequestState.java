package com.laudado.talentforgeaibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestState<T> {
    private T data=null;
    private boolean isLoading;
    private String error=null;
}
