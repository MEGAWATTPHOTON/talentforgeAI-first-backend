package com.laudado.talentforgeaibackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private String country;
    private String state;
    private String city;
    private String postalCode;
    private String address;
}
