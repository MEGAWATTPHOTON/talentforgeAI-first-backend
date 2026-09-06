package com.laudado.talentforgeaibackend.dto;

import com.laudado.talentforgeaibackend.enums.ApplicationStage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationFilters {
    private String jobId;
    private List<ApplicationStage> stage;
    private String companyId;
    private String search;
}
