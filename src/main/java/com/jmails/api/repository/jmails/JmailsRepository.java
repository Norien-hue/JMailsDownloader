package com.jmails.api.repository.jmails;

import com.jmails.api.entity.Jmail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JmailsRepository extends MongoRepository<Jmail, String> {

    Page<Jmail> findAll(Pageable pageable);
}