package com.laudado.talentforgeaibackend.models;

import com.laudado.talentforgeaibackend.enums.CompanyVerificationStatus;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Set;

@Document(collection = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    private ObjectId id;

    private String name;

    private Set<String> emailDomains;

    private String websiteUrl;
    private String description;
    private String logoUrl;
    private String industry;
    private Integer employeeCount;
    private Year foundedYear;
    private String location;
    private String bannerUrl;
    private String slug;
    private Integer followersCount;


    @Email
    private String contactEmail;

    private Set<String> culture;
    private Set<String> benefits;
    private List<String> galleryImages;
    private Integer activeJobsCount;

    private String contactPhone;

    private CompanyVerificationStatus verificationStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}