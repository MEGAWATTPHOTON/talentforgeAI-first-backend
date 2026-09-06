package com.laudado.talentforgeaibackend.dto.responseDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyEmailResponse {
    private boolean verified;
    private String email;
}
