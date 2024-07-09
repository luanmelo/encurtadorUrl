package com.luanmelo.encurtadorurl.repository;

import com.luanmelo.encurtadorurl.entities.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
}
