package com.tahauddin.syed.service;

import com.tahauddin.syed.domain.Movie;
import com.tahauddin.syed.domain.MovieEvents;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieService {

    Mono<Movie> getMovieById(String id);

    Flux<Movie> findAll();

    Flux<MovieEvents> streamMovieEvents(String id);
}
