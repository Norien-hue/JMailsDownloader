package com.jmails.api.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.jmails.api.repository.jmails",
        mongoTemplateRef = "jmailsMongoTemplate"
)
public class JmailsMongoConfig {

    @Value("${spring.mongodb.uri}")
    private String mongoUri;

    @Value("${mongodb.jmails.database}")
    private String database;

    @Bean(name = "jmailsMongoClient")
    public MongoClient jmailsMongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Bean(name = "jmailsMongoDatabaseFactory")
    public MongoDatabaseFactory jmailsMongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(jmailsMongoClient(), database);
    }

    @Bean(name = "jmailsMongoTemplate")
    public MongoTemplate jmailsMongoTemplate() {
        return new MongoTemplate(jmailsMongoDatabaseFactory());
    }
}