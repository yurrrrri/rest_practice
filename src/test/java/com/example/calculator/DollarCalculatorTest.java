package com.example.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DollarCalculatorTest {

    @Mock
    public MarketApi marketApi;

    @BeforeEach
    public void init() {
        Mockito.lenient().when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    void dollarTest() {
        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);

        assertThat(calculator.sum(10, 10)).isEqualTo(24000);
        assertThat(calculator.minus(10, 10)).isEqualTo(0);
    }

    @Test
    void mockDollarTest() {
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        Calculator calculator = new Calculator(dollarCalculator);

        assertThat(calculator.sum(10, 10)).isEqualTo(60000);
        assertThat(calculator.minus(10, 10)).isEqualTo(0);
    }

}