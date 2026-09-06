package com.laudado.talentforgeaibackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education{
    private String institution;
    private String fieldOfStudy;
    private String degree;
    private Date startDate;
    private Date endDate;
}