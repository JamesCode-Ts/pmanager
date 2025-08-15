package com.java360.pmanager.domain.repository;

import com.java360.pmanager.domain.document.ApiKey;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApiKeyRepository extends MongoRepository<ApiKey, String> {
}
