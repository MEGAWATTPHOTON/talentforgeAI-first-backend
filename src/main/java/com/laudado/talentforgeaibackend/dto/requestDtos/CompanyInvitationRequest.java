package com.laudado.talentforgeaibackend.dto.requestDtos;

import com.laudado.talentforgeaibackend.enums.CompanyRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInvitationRequest {

    @NotNull
    private String recruiterId;

    @NotNull
    private CompanyRole role;
}