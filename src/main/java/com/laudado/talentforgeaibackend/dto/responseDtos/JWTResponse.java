package com.laudado.talentforgeaibackend.dto.responseDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    /** Seconds until the access token expires. */
    private Long expiresIn;
}
