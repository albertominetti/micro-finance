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

    @GetMapping("/rates/{target}/{reference}")
    public ForexRate rates(@PathVariable String target, @PathVariable String reference) {
        ForexService.ForexResponse latestRate = forexService.getLatestRate(target, reference);
        return new ForexRate(target, reference, latestRate.getRate(), latestRate.getDate());
    }


    @GetMapping("/history/{target}/{reference}")
    public Object history(@PathVariable String target, @PathVariable String reference,
                          @RequestParam LocalDate from, @RequestParam LocalDate to) {
        return forexService.getHistoricalRates(target, reference, from, to);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ForexRate {
        private String target;
        private String reference;
        private BigDecimal value;
        private LocalDate date;
    }
}
