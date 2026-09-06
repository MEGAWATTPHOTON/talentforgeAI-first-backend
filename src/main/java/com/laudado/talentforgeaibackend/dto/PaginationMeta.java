package com.laudado.talentforgeaibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationMeta {
    private int page;
    private int pageSize;
    private int totalItems;
    private int totalPages;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
}
