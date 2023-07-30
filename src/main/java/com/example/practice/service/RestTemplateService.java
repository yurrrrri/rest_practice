package com.example.practice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class RestTemplateService {

    public String hello() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .encode()
                .build()
                .toUri();
        log.info("{}", uri);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        log.info("{}", result.getStatusCode());
        log.info("{}", result.getBody());

        return result.getBody();
    }

}
