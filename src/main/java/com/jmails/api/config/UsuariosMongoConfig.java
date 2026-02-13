package com.jmails.api.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "com.jmails.api.repository.usuarios",
        mongoTemplateRef = "usuariosMongoTemplate"
)
public class UsuariosMongoConfig {

    @Value("${spring.mongodb.uri}")
    private String mongoUri;

    @Value("${mongodb.usuarios.database}")
    private String database;

    @Primary
    @Bean(name = "usuariosMongoClient")
    public MongoClient usuariosMongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Primary
    @Bean(name = "usuariosMongoDatabaseFactory")
    public MongoDatabaseFactory usuariosMongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(usuariosMongoClient(), database);
    }

    @Primary
    @Bean(name = "usuariosMongoTemplate")
    public MongoTemplate usuariosMongoTemplate() {
        return new MongoTemplate(usuariosMongoDatabaseFactory());
    }
}