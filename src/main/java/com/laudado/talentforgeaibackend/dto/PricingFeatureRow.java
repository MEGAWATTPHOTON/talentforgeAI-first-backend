package com.laudado.talentforgeaibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PricingFeatureRow {

    private String label;
    private Object starter;
    private Object professional;
    private Object enterprise;
}
