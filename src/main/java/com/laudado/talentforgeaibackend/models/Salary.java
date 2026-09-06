package com.laudado.talentforgeaibackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salary {
    private BigDecimal min;
    private BigDecimal max;
    private String currency;
}
