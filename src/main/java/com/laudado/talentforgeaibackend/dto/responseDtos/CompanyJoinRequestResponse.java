package com.laudado.talentforgeaibackend.dto.responseDtos;

import com.laudado.talentforgeaibackend.enums.JoinRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyJoinRequestResponse {

    private String id;

    private String userId;

    private String companyId;

    private JoinRequestStatus status;

    private String message;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
