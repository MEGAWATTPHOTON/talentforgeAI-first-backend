package com.laudado.talentforgeaibackend.mappers;

import com.laudado.talentforgeaibackend.dto.requestDtos.RegisterCompanyRequest;
import com.laudado.talentforgeaibackend.enums.CompanyVerificationStatus;
import com.laudado.talentforgeaibackend.models.Company;
import com.laudado.talentforgeaibackend.models.Location;
import com.laudado.talentforgeaibackend.services.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
@Component
@RequiredArgsConstructor
public class RegisterCompanyRequestToCompanyMapper {
    private final CloudinaryService service;
    public Company toCompany(RegisterCompanyRequest request){
        Company company=new Company();
        company.setName(request.getName());
        company.setEmailDomains(new HashSet<>(request.getEmailDomains()));
        company.setWebsiteUrl(request.getWebsite());
        company.setIndustry(request.getIndustry());
        company.setLocation(request.getCountry()+", "+request.getCity());
        company.setDescription(request.getDescription());
        company.setLogoUrl(service.uploadFile(request.getLogo()));
        company.setVerificationStatus(CompanyVerificationStatus.PENDING);
        LocalDateTime now=LocalDateTime.now();
        company.setCreatedAt(now);
        company.setUpdatedAt(now);
        return company;
    }
}
