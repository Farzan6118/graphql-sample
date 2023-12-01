package com.example.graphql;

import com.example.graphql.model.Country;
import com.example.graphql.repository.CountryRepository;
import com.example.graphql.service.CountryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

import java.util.List;

@SpringBootApplication
public class GraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CountryService service, CountryRepository repository) {
        return args -> {
            Mono<List<Country>> countries = service.getCountries();
            countries.subscribe(
                    repository::saveAll);
        };
    }
}
