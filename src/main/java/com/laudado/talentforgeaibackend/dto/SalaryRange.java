package com.laudado.talentforgeaibackend.dto;

import com.laudado.talentforgeaibackend.enums.SalaryPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryRange {

    private BigDecimal min;
    private BigDecimal max;
    private String currency;
    private SalaryPeriod period;
}