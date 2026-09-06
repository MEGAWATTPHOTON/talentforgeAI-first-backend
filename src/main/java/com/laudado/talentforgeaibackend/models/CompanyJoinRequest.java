package com.laudado.talentforgeaibackend.models;

import com.laudado.talentforgeaibackend.enums.JoinRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "company_join_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyJoinRequest {

    @Id
    private ObjectId id;

    private ObjectId userId;
    private ObjectId companyId;

    private JoinRequestStatus status;

    private String message;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}