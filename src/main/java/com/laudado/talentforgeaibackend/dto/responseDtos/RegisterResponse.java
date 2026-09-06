package com.laudado.talentforgeaibackend.dto.responseDtos;

import com.laudado.talentforgeaibackend.dto.AuthUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private AuthUser user;
    /** True when the backend requires email confirmation before login. */
    private boolean requiresEmailVerification;
}
