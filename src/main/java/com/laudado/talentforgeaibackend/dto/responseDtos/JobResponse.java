package com.laudado.talentforgeaibackend.dto.responseDtos;

import com.laudado.talentforgeaibackend.dto.CompanySummary;
import com.laudado.talentforgeaibackend.dto.SalaryRange;
import com.laudado.talentforgeaibackend.enums.ExperienceLevel;
import com.laudado.talentforgeaibackend.enums.JobStatus;
import com.laudado.talentforgeaibackend.enums.JobType;
import com.laudado.talentforgeaibackend.enums.WorkMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {

    private String id;
    private String title;
    private String slug;
    private String companyId;
    private String description;
    private List<String> responsibilities;
    private List<String> requirements;
    private List<String> benefits;
    private List<String> skills;
    private JobType type;
    private WorkMode workMode;
    private ExperienceLevel experienceLevel;
    private String location;
    private SalaryRange salaryRange;
    private JobStatus status;
    private Integer applicationsCount;
    private Integer viewsCount;
    private String postedByUserId;
    private LocalDateTime publishedAt;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CompanySummary company;
}