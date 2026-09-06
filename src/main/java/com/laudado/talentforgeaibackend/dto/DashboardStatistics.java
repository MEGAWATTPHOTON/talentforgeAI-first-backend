package com.laudado.talentforgeaibackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatistics {

    private List<DashboardStatCard> cards;
    private List<TimeSeriesPoint> applicationsOverTime;
    private List<TimeSeriesPoint> hiresOverTime;
    private List<TopJob> topJobs;
}
