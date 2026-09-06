package com.laudado.talentforgeaibackend.models;

import com.laudado.talentforgeaibackend.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Profile{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Gender gender;
    private LocalDate dateOfBirth;
    private Location location;
    private String bio;
    private String profilePictureUrl;
}
