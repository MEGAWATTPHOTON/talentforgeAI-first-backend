package com.laudado.talentforgeaibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Testimonial {

    private String id;
    private String quote;
    private String authorName;
    private String authorTitle;
    private String authorCompany;
    private String avatarUrl;
}