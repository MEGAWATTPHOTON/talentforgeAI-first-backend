package com.laudado.talentforgeaibackend.dto;

import com.laudado.talentforgeaibackend.enums.ApplicationStage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    private String id;
    private String jobId;
    private String candidateUserId;
    private String companyId;
    private ApplicationStage stage;
    private String coverLetter;
    private String resumeUrl;
    private Long matchScore;
    private List<ApplicationTimelineEvent> timeline;
    private String appliedAt;
    private String updatedAt;
}
