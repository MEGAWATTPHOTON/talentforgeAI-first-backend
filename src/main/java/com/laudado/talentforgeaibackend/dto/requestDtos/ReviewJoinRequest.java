package com.laudado.talentforgeaibackend.dto.requestDtos;

import com.laudado.talentforgeaibackend.enums.JoinRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewJoinRequest {

    private JoinRequestStatus status;
}