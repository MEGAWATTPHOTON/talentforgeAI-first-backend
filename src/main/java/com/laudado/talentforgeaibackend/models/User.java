package com.laudado.talentforgeaibackend.models;

import com.laudado.talentforgeaibackend.enums.Role;
import com.laudado.talentforgeaibackend.enums.UserStatus;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;

    @Indexed(unique=true)
    private String username;

    @Indexed(unique=true)
    @Email
    private String email;

    private String password;
    private Set<Role> roles;
    private UserStatus status;
    private boolean emailIsVerified;
    private Profile userProfile;
    private JobSeekerProfile jobSeekerProfile;
    private RecruiterProfile recruiterProfile;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
