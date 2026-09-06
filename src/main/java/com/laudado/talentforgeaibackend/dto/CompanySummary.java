package com.laudado.talentforgeaibackend.dto;

import com.laudado.talentforgeaibackend.enums.CompanyVerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanySummary {

    private String id;
    private String name;
    private String slug;
    private String logoUrl;
    private CompanyVerificationStatus verificationStatus;
}