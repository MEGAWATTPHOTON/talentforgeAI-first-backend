package com.laudado.talentforgeaibackend.dto;

import com.laudado.talentforgeaibackend.enums.ExperienceLevel;
import com.laudado.talentforgeaibackend.enums.JobStatus;
import com.laudado.talentforgeaibackend.enums.JobType;
import com.laudado.talentforgeaibackend.enums.WorkMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobFilters {

    private String search;
    private List<JobType> type;
    private List<WorkMode> workMode;
    private List<ExperienceLevel> experienceLevel;
    private String location;
    private String companyId;
    private List<String> companyIds;
    private List<String> skills;
    private List<JobStatus> status;
    private BigDecimal salaryMin;
}