package com.example.practice.swagger.controller;

import com.example.practice.swagger.dto.UserReq;
import com.example.practice.swagger.dto.UserRes;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"API 정보를 제공하는 Controller"})
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/plus/{x}")
    public int plus(
            @ApiParam(value = "x값")
            @PathVariable int x,
            @ApiParam(value = "y값")
            @RequestParam int y
    ) {
        return x + y;
    }

    @ApiResponse(code = 502, message = "사용자의 나이가 10살 이하일 때")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "req", value = "UserReq", required = true)
    })
    @ApiOperation(value = "사용자의 이름과 나이를 echo하는 메서드")
    @GetMapping("/user")
    public UserRes user(UserReq req) {
        return new UserRes(req.getName(), req.getAge());
    }

    @ApiOperation(value = "userPost")
    @PostMapping("/user")
    public UserRes userPost(@RequestBody UserReq req) {
        return new UserRes(req.getName(), req.getAge());
    }

}
