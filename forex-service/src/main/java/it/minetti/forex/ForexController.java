package it.minetti.forex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
public class ForexController {

    @Autowired
    private ForexService forexService;

    @GetMapping("/rates/{base}/{target}")
    public ForexRate rates(@PathVariable String base, @PathVariable String target) {
        ForexService.ForexResponse latestRate = forexService.getLatestRate(base, target);
        return new ForexRate(base, target, latestRate.getRate(), latestRate.getDate());
    }


    @GetMapping("/history/{base}/{target}")
    public Object history(@PathVariable String base, @PathVariable String target,
                                  @RequestParam LocalDate from, @RequestParam LocalDate to) {
        return forexService.getHistoricalRates(base, target, from, to);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ForexRate {
        private String base;
        private String target;
        private BigDecimal value;
        private LocalDate date;
    }
}
