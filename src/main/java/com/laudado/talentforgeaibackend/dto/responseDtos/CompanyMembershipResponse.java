package com.laudado.talentforgeaibackend.dto.responseDtos;

import com.laudado.talentforgeaibackend.enums.CompanyRole;
import com.laudado.talentforgeaibackend.enums.MembershipStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyMembershipResponse {

    private String id;

    private String userId;

    private String companyId;

    private CompanyRole role;

    private MembershipStatus status;

    private LocalDateTime joinedAt;
}