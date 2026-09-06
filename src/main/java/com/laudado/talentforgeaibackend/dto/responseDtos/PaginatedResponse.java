package com.laudado.talentforgeaibackend.dto.responseDtos;

import com.laudado.talentforgeaibackend.dto.PaginationMeta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponse <T>{
    private List<T> items;
    private PaginationMeta meta;
}
