package com.laudado.talentforgeaibackend.mappers;

import com.laudado.talentforgeaibackend.dto.responseDtos.CompanyResponse;
import com.laudado.talentforgeaibackend.enums.CompanySize;
import com.laudado.talentforgeaibackend.models.Company;

import java.util.ArrayList;

public class CompanyToCompanyResponseMapper {
    public static CompanyResponse toDto(Company company){
        CompanyResponse response=new CompanyResponse();
        response.setId(company.getId().toString());
        response.setName(company.getName());
        response.setSlug(company.getSlug());
        response.setEmailDomains(company.getEmailDomains().stream().toList());
        response.setLogoUrl(company.getLogoUrl());
        response.setBannerUrl(company.getBannerUrl());
        response.setWebsite(company.getWebsiteUrl());
        response.setIndustry(company.getIndustry());
        response.setActiveJobsCount(company.getActiveJobsCount());
        response.setFollowersCount(company.getFollowersCount());
        response.setCulture(new ArrayList<>(company.getCulture()));
        response.setBenefits(company.getBenefits().stream().toList());
        response.setSize(CompanySize.fromEmployeeCount(company.getEmployeeCount()));
        response.setDescription(company.getDescription());
        response.setLocation(company.getLocation());
        response.setFoundedYear(company.getFoundedYear().getValue());
        response.setVerificationStatus(company.getVerificationStatus());
        response.setCreatedAt(company.getCreatedAt());
        response.setUpdatedAt(company.getUpdatedAt());
        response.setGalleryImages(company.getGalleryImages());
        return response;
    }
}
