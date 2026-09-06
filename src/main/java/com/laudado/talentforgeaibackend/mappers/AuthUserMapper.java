package com.laudado.talentforgeaibackend.mappers;

import com.laudado.talentforgeaibackend.dto.AuthUser;
import com.laudado.talentforgeaibackend.models.User;

public class AuthUserMapper {
    public static AuthUser toDto(User user) {
        return new AuthUser(user.getId().toString(),user.getEmail(),user.getUserProfile().getFirstName(),
                user.getUserProfile().getLastName(),user.getRoles(),false,
                user.getUserProfile().getProfilePictureUrl());
    }
}
