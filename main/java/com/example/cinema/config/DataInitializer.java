package com.example.cinema.config;

import com.example.cinema.model.Movie;
import com.example.cinema.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner init(MovieRepository movieRepository) {
        return args -> {
            movieRepository.deleteAll();
            Movie m1 = new Movie();
            m1.setTitle(\"黄金时代的少年\");
            m1.setDuration(125);
            m1.setRating(8.4);
            m1.setActors(\"张三,李四\");
            movieRepository.save(m1);

            Movie m2 = new Movie();
            m2.setTitle(\"末法时代的圣骑士\");
            m2.setDuration(142);
            m2.setRating(9.0);
            m2.setActors(\"王五,赵六\");
            movieRepository.save(m2);
        };
    }
}
