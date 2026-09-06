package com.laudado.talentforgeaibackend.dto;

import com.laudado.talentforgeaibackend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    /* A user can hold more than one role (e.g. an Admin who is also a Recruiter). */
    private Set<Role> roles;
    private boolean emailVerified;
    private String avatarUrl;
}
