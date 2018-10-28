package org.example.cards.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.cards.model.Card;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(CardRepository repository) {
        Logger logger = LogManager.getLogger(LoadDatabase.class);
        return args -> {
            logger.info("Preloading " + repository.save(new Card("apple", "asdfasdf","a.png")));
            logger.info("Preloading " + repository.save(new Card("pear", "asdf", "b.png")));
        };
    }
}
