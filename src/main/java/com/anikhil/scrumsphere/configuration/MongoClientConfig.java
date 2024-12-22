package com.anikhil.scrumsphere.configuration;

import com.mongodb.client.MongoClient;
import jakarta.annotation.PreDestroy;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class MongoClientConfig {

    private final MongoClient mongoClient;

    public MongoClientConfig(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @PreDestroy
    public void closeMongoClient() {

        LOGGER.info("Implementing Shutdown Hook");
        LOGGER.info("Closing Mongo Client");
        if (this.mongoClient != null) {
            this.mongoClient.close();
        }
    }


}
