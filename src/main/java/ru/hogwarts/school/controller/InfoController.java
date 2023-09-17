package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.LongStream;
import java.util.stream.Stream;

@RestController
@RequestMapping("/properties")
public class InfoController {
    @Value("${server.port:-1}")
    private int port;

    @GetMapping("/port")
    public int getPort() {
        return port;
    }

    @GetMapping("/sum")
    public String calcSum() {
        long t = System.currentTimeMillis();
//        Long result = Stream.iterate(1L, a -> a + 1)
//                .limit(1_000_000)
//                .reduce(0L, (a, b) -> a + b);
        Long result = LongStream.range(1,50_000_001)
                .parallel()
                .sum();
        long timeConsumed = System.currentTimeMillis() - t;
        return "time consumed = " + timeConsumed + " ms. Result = " + result;
    }
}
