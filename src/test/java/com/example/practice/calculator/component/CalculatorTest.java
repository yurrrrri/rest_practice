package com.example.practice.calculator.component;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CalculatorTest {

    @MockBean
    private MarketApi marketApi;

    @Autowired
    private Calculator calculator;

    @Test
    void dollarCalculatorTest() {
        Mockito.when(marketApi.connect()).thenReturn(3000);

        assertThat(calculator.sum(10, 10)).isEqualTo(60000);
        assertThat(calculator.minus(10, 10)).isZero();
    }
}
