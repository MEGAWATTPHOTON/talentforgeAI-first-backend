package com.laudado.talentforgeaibackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerProfile{
    private String resumeUrl;
    private String coverLetterUrl;
    private String portfolioUrl;
    private String linkedinUrl;
    private String githubUrl;
    private Set<String> skills;
    private List<Education> education;
    private List<Experience> experience;
}
