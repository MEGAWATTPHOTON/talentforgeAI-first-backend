package com.laudado.talentforgeaibackend.dto;

import com.laudado.talentforgeaibackend.enums.SortDirection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationParams {
    private int page;
    private int pageSize;
    private String sortBy;
    private SortDirection sortDirection;
}
