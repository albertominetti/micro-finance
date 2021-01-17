package it.minetti.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class MarketController {

    @Autowired
    private MarketService marketService;


    @GetMapping("/prices/{symbol}")
    public Object history(@PathVariable String symbol,
                                  @RequestParam LocalDate from, @RequestParam LocalDate to) {
        return marketService.getHistoricalRates(symbol, from, to);
    }

}
