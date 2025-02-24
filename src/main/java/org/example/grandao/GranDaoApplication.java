package org.example.grandao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.grandao.Repositorio")
@EnableMongoRepositories(basePackages = "org.example.grandao.RepositorioMDB")
public class GranDaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(GranDaoApplication.class, args);
    }
}