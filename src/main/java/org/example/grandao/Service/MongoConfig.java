package org.example.grandao.Service;

import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;


//Configuracion para que la parte de Mongo funcione
@Configuration
public class MongoConfig {

    //Conecta con la bd biblioteca
    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "biblioteca");
    }
}

