package com.example.practice.rest.service;

import com.example.practice.rest.dto.Req;
import com.example.practice.rest.dto.UserRequest;
import com.example.practice.rest.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
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

    public UserResponse exchange() {
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

        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "efg")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> res = restTemplate
                .exchange(requestEntity, UserResponse.class);

        return res.getBody();
    }

    public Req<UserResponse> genericExchange() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .buildAndExpand(100, "steve")
                .toUri();
        log.info("{}", uri);

        UserRequest userRequest = new UserRequest();
        userRequest.setName("steve");
        userRequest.setAge(10);

        Req<UserRequest> req = new Req<>();
        req.setHeader(new Req.Header());
        req.setResponseBody(userRequest);

        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "efg")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Req<UserResponse>> response = restTemplate.exchange(
                requestEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        return response.getBody();
    }

}
