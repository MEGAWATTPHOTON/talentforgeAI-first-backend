package com.laudado.talentforgeaibackend.dto;

import com.laudado.talentforgeaibackend.enums.DigestFrequency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationPreferences {

    private boolean emailEnabled;
    private boolean pushEnabled;
    private DigestFrequency digestFrequency;
}