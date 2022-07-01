package com.tahauddin.syed.bootstrap;

import com.tahauddin.syed.domain.Movie;
import com.tahauddin.syed.repo.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
@Slf4j
public class MovieDataLoader implements CommandLineRunner {

    private final MovieRepository movieRepository;

    @Override
    public void run(String... args) throws Exception {

        movieRepository.deleteAll()
                .thenMany(
                        Flux.just("Thor", "Doctor Strange", "The Avengers", "The Batman", "Spider Man", "Iron Man").map(title -> new Movie(title))
                                .flatMap(movieRepository :: save)
                ).subscribe(null,
                        null,
                        () -> movieRepository.findAll()
                                    .subscribe(m -> log.info("Movie is :: {}", m))
                );
    }
}
