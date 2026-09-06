package com.laudado.talentforgeaibackend.models;

import com.laudado.talentforgeaibackend.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    private ObjectId id;
    private ObjectId applicantId;
    private ObjectId jobId;
    private String coverLetterUrl;
    private String resumeUrl;
    private ApplicationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
