package com.laudado.talentforgeaibackend.models;

import com.laudado.talentforgeaibackend.enums.CompanyRole;
import com.laudado.talentforgeaibackend.enums.MembershipStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "company_memberships")
@CompoundIndex(
        name = "user_company_unique",
        def = "{'userId': 1, 'companyId': 1}",
        unique = true
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyMembership {

    @Id
    private ObjectId id;

    private ObjectId userId;
    private ObjectId companyId;

    private CompanyRole role;
    private MembershipStatus status;

    private LocalDateTime joinedAt;
}