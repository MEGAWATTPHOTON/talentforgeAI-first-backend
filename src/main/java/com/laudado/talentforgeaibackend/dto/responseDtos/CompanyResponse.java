package com.laudado.talentforgeaibackend.dto.responseDtos;

import com.laudado.talentforgeaibackend.enums.CompanySize;
import com.laudado.talentforgeaibackend.enums.CompanyVerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {

    private String id;

    private String name;

    private String slug;

    private List<String> emailDomains;

    private String logoUrl;

    private String bannerUrl;

    private String website;

    private String industry;

    private CompanySize size;

    private String description;

    private String location;

    private Integer foundedYear;

    private CompanyVerificationStatus verificationStatus;

    private Integer activeJobsCount;

    private Integer followersCount;

    private List<String> culture;

    private List<String> benefits;

    private List<String> galleryImages;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
