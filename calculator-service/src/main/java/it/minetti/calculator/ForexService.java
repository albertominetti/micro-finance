package it.minetti.calculator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient("forex-service")
interface ForexService {

    @GetMapping("/rates/{base}/{target}")
    public ForexRate rates(@PathVariable String base, @PathVariable String target);

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ForexRate {
        private String base;
        private String target;
        private BigDecimal value;
    }
}
