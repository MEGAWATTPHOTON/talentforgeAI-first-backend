package com.laudado.talentforgeaibackend.repositories;

import com.laudado.talentforgeaibackend.models.VerificationToken;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends MongoRepository<VerificationToken, ObjectId> {
    Optional<VerificationToken> findByTokenHash(String hash);

    void deleteByUserId(ObjectId id);
}
