package com.example.practice.service;

import com.example.practice.dto.UserRequest;
import com.example.practice.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class RestTemplateService {

    public UserResponse getHello() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name", "steve")
                .queryParam("age", 10)
                .encode()
                .build()
                .toUri();
        log.info("{}", uri);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> result = restTemplate
                .getForEntity(uri, UserResponse.class);

        log.info("{}", result.getStatusCode());
        log.info("{}", result.getBody());

        return result.getBody();
    }

    public UserResponse post() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .buildAndExpand(100, "steve")
                .toUri();
        log.info("{}", uri);

        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> res = restTemplate
                .postForEntity(uri, req, UserResponse.class);

        log.info("{}", res.getStatusCode());
        log.info("{}", res.getHeaders());
        log.info("{}", res.getBody());

        return res.getBody();
    }

}
