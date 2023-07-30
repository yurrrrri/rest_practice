package com.example.practice.rest.controller;

import com.example.practice.rest.dto.Req;
import com.example.practice.rest.dto.UserResponse;
import com.example.practice.rest.service.RestTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ApiController {

    private final RestTemplateService restTemplateService;

    public ApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/hello")
    public Req<UserResponse> getHello() {
        return restTemplateService.genericExchange();
    }

}
