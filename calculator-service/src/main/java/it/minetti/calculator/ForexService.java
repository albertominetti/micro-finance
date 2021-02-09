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

    @GetMapping("/rates/{target}/{reference}")
    ForexRate rates(@PathVariable String target, @PathVariable String reference);

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class ForexRate {
        private String target;
        private String reference;
        private BigDecimal value;
    }
}
