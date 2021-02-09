package it.minetti.calculator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static java.util.Collections.emptyMap;

@FeignClient("market-service")
interface MarketService {

    @GetMapping("/prices/daily/{symbol}")
    DailyPrices prices(@PathVariable String symbol);

    @GetMapping("/exchanges/{mic}")
    Exchange exchange(@PathVariable String mic);

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class DailyPrices {
        private Map<LocalDate, BigDecimal> prices = emptyMap();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Exchange {
        private String name;
        private Currency currency;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Currency {
        private String code;
    }

}
