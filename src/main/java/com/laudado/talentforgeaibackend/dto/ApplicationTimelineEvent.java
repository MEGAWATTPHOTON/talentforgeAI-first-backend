package com.laudado.talentforgeaibackend.dto;

import com.laudado.talentforgeaibackend.enums.ApplicationStage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationTimelineEvent {
    private String id;
    private ApplicationStage stage;
    private String note;
    private String createdAt;
    private String actorUserId;
}
