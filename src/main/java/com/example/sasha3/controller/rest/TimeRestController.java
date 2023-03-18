package com.example.sasha3.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@RestController
public class TimeRestController {
    @GetMapping(value = "/time-events", produces = "text/event-stream")
    public Flux<String> timeEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
}
