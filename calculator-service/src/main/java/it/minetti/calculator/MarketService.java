package it.minetti.calculator;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("market-service")
interface MarketService {

    @GetMapping("/prices/{symbol}")
    public String prices(@PathVariable String symbol);

}
