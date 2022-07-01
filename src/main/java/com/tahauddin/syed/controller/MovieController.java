package com.tahauddin.syed.controller;

import com.tahauddin.syed.domain.Movie;
import com.tahauddin.syed.domain.MovieEvents;
import com.tahauddin.syed.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    public Mono<Movie> getById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/all")
    public Flux<Movie> getALl(){
        return movieService.findAll();
    }

    @GetMapping(value = "/stream/{id}", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<MovieEvents> getMovieStream(@PathVariable String id){

        return movieService.streamMovieEvents(id);
    }
}
