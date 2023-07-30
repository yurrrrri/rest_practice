package com.example.practice.calculator.controller;

import com.example.practice.calculator.component.Calculator;
import com.example.practice.calculator.dto.Req;
import com.example.practice.calculator.dto.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CalculatorApiController {

    private final Calculator calculator;

    @GetMapping("/sum")
    public int sum(@RequestParam int x, @RequestParam int y) {
        return calculator.sum(x, y);
    }

    @PostMapping("/minus")
    public Res minus(@RequestBody Req req) {
        int result = calculator.minus(req.getX(), req.getY());

        Res res = new Res();
        res.setResult(result);

        return res;
    }

}
