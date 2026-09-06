package com.laudado.talentforgeaibackend.repositories;

import com.laudado.talentforgeaibackend.models.CompanyMembership;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyMembershipRepository extends MongoRepository<CompanyMembership, ObjectId> {
    List<CompanyMembership> findByCompanyId(ObjectId companyId);

    long deleteByIdAndCompanyId(ObjectId membershipId, ObjectId companyId);

    Optional<Object> findByUserIdAndCompanyId(ObjectId UserId, ObjectId companyId);
}
