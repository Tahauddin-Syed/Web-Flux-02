package com.tahauddin.syed.service.impl;

import com.tahauddin.syed.domain.Movie;
import com.tahauddin.syed.domain.MovieEvents;
import com.tahauddin.syed.repo.MovieRepository;
import com.tahauddin.syed.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;


    @Override
    public Mono<Movie> getMovieById(String id) {
        return movieRepository.findById(id);
    }

    @Override
    public Flux<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Flux<MovieEvents> streamMovieEvents(String id) {
        return Flux.<MovieEvents>generate(movieEventsSynchronousSink -> {
            movieEventsSynchronousSink.next(new MovieEvents(id, LocalDate.now()));
        }).delayElements(Duration.ofSeconds(1));
    }
}
