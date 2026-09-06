package com.laudado.talentforgeaibackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatCard {

    private String id;
    private String label;
    private Object value;
    private Integer change;
    private String changeDirection;
    private String icon;
}
