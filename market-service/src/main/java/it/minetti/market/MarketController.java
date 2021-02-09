package it.minetti.market;

import it.minetti.market.marketstack.ExchangesResponse;
import it.minetti.market.marketstack.ExchangesResponse.Exchange;
import it.minetti.market.model.DailyPrices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class MarketController {

    @Autowired
    private MarketService marketService;


    @GetMapping("/prices/daily/{symbol}")
    public DailyPrices history(@PathVariable String symbol,
                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        if (from == null && to == null) {
           return marketService.getLatestRate(symbol);
        }

        return marketService.getHistoricalRates(symbol, from, to);
    }


    @GetMapping("/exchanges/{mic}")
    public Exchange exchange(@PathVariable String mic) {
        return marketService.getExchange(mic);
    }


}
