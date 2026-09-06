package com.laudado.talentforgeaibackend.dto.requestDtos;

import com.laudado.talentforgeaibackend.enums.CompanySize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCompanyRequest {

    private String name;

    private List<String> emailDomains;

    private String website;

    private String industry;

    private CompanySize size;

    private String country;

    private String city;

    private String description;

    private MultipartFile logo;
}
