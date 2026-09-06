package com.laudado.talentforgeaibackend.models;

import com.laudado.talentforgeaibackend.enums.EmploymentType;
import com.laudado.talentforgeaibackend.enums.JobStatus;
import com.laudado.talentforgeaibackend.enums.WorkMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Document(collection="jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    private ObjectId id;
    private ObjectId companyId;
    private String title;
    private String description;
    private List<String> responsibilities;
    private List<String> requirements;
    private Set<String> skills;
    private EmploymentType employmentType;
    private WorkMode workMode;
    private Location location;
    private Salary salary;
    private LocalDate applicationDeadline;
    private JobStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
