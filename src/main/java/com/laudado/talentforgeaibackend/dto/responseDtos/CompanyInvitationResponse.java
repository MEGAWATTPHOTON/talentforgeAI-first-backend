package com.laudado.talentforgeaibackend.dto.responseDtos;

import com.laudado.talentforgeaibackend.enums.CompanyRole;
import com.laudado.talentforgeaibackend.enums.InvitationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInvitationResponse {

    private String id;

    private String companyId;

    private String recruiterId;

    private CompanyRole role;

    private InvitationStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;
}