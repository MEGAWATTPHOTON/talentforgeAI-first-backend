package com.laudado.talentforgeaibackend.dto.requestDtos;

import com.laudado.talentforgeaibackend.enums.Gender;
import com.laudado.talentforgeaibackend.validation.MinimumAge;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterJobseekerRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Select an option")
    private Gender gender;

    // ISO date (yyyy-MM-dd) — mirrors lib/validation.ts jobSeekerStep1Schema.dateOfBirth
    @NotBlank(message = "Date of birth is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of birth must be in yyyy-MM-dd format")
    @MinimumAge(min = 16, max = 100, message = "You must be at least 16 years old")
    private String dateOfBirth;

    @NotBlank(message = "Select your country")
    private String country;

    // Mirrors lib/validation.ts phoneSchema
    @NotBlank(message = "Enter a valid phone number")
    @Pattern(regexp = "^[+]?[\\d\\s()-]{7,20}$", message = "Enter a valid phone number")
    private String phoneNumber;

    // Mirrors lib/validation.ts usernameSchema
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 24, message = "Must be between 3 and 24 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_.]+$", message = "Only letters, numbers, dots, and underscores")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    private String email;

    // Mirrors lib/validation.ts passwordSchema — four separate patterns so
    // each failure maps to one clear message, same as the frontend does.
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Must be at least 8 characters")
    @Pattern(regexp = ".*[A-Z].*", message = "Must include an uppercase letter")
    @Pattern(regexp = ".*[a-z].*", message = "Must include a lowercase letter")
    @Pattern(regexp = ".*[0-9].*", message = "Must include a number")
    @Pattern(regexp = ".*[^A-Za-z0-9].*", message = "Must include a special character")
    private String password;

    /**
     * Uploads are captured client-side only in this mock frontend (see
     * components/forms/FileUpload.tsx) — a real backend integration would
     * replace these with URLs returned from a Cloudinary/S3 upload step.
     * All three are optional — validate emptiness in the service layer
     * (MultipartFile.isEmpty()), not here.
     */
    private MultipartFile profilePicture;
    private MultipartFile resume;
    private MultipartFile coverLetter;

    // Mirrors lib/validation.ts optionalUrlSchema() — blank is fine, but a
    // non-blank value must look like a URL.
    @Pattern(regexp = "^$|^https?://.+", message = "Enter a valid URL (https://…)")
    private String portfolioUrl;

    @Pattern(regexp = "^$|^https?://.+", message = "Enter a valid URL (https://…)")
    private String linkedinUrl;

    @Pattern(regexp = "^$|^https?://.+", message = "Enter a valid URL (https://…)")
    private String githubUrl;
}
