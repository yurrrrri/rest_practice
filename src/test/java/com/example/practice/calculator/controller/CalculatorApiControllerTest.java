package com.example.practice.calculator.controller;

import com.example.practice.calculator.component.Calculator;
import com.example.practice.calculator.component.DollarCalculator;
import com.example.practice.calculator.component.MarketApi;
import com.example.practice.calculator.dto.Req;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalculatorApiController.class)
@AutoConfigureWebMvc
@Import({Calculator.class, DollarCalculator.class})
class CalculatorApiControllerTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void init() {
        Mockito.when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    void sumTest() throws Exception {
        mvc.perform(get("http://localhost:8080/api/sum")
                        .queryParam("x", "10")
                        .queryParam("y", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string("60000"))
                .andDo(print());
    }

    @Test
    void minusTest() throws Exception {
        Req req = new Req();
        req.setX(10);
        req.setY(10);

        String json = new ObjectMapper().writeValueAsString(req);

        mvc.perform(post("http://localhost:8080/api/minus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("0"))
                .andExpect(jsonPath("$.response.resultCode").value("OK"))
                .andDo(print());
    }

}