package com.laudado.talentforgeaibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PricingPlan {

    private String id;
    private String name;
    private String tagline;
    private BigDecimal monthlyPrice;
    private BigDecimal annualPrice;
    private boolean highlighted;
    private String ctaLabel;
    private List<String> features;
}
