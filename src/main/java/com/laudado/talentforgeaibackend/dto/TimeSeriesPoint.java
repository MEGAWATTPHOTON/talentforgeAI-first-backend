package com.laudado.talentforgeaibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSeriesPoint {

    private String date;
    private Double value;
    private String label;
}