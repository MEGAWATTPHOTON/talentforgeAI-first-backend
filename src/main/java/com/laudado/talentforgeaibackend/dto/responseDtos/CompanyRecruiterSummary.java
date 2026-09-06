package com.laudado.talentforgeaibackend.dto.responseDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRecruiterSummary {

    private String id;

    private String firstName;

    private String lastName;

    private String title;

    private String avatarUrl;
}
