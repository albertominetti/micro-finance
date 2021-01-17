package it.minetti.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CalculatorController {

    @Autowired
    private ForexService forexService;

    @GetMapping("/prices/{listingId}/{currency}")
    public BigDecimal prices(@PathVariable String listingId, @PathVariable String currency) {

        BigDecimal onePrice = BigDecimal.ONE;
        String oneCurrency = "RUB";

        ForexService.ForexRate rate = forexService.rates(oneCurrency, currency);

        return onePrice.multiply(rate.getValue());

    }
}
