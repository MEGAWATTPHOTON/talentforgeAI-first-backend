package com.laudado.talentforgeaibackend.repositories;

import com.laudado.talentforgeaibackend.models.CompanyJoinRequest;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CompanyJoinRequestRepository extends MongoRepository<CompanyJoinRequest, ObjectId> {
    List<CompanyJoinRequest> findByCompanyId(ObjectId companyId);

    long deleteByIdAndCompanyId(ObjectId requestId, ObjectId companyId);

    CompanyJoinRequest findByIdAndCompanyId(ObjectId objectId, ObjectId objectId1);
}
