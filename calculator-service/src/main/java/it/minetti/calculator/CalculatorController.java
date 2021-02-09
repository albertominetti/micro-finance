package it.minetti.calculator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static com.google.common.collect.Iterables.getOnlyElement;

@RestController
public class CalculatorController {

    private final ForexService forexService;
    private final MarketService marketService;

    public CalculatorController(ForexService forexService, MarketService marketService) {
        this.forexService = forexService;
        this.marketService = marketService;
    }

    @GetMapping("/prices/{symbol}/{referenceCurrency}")
    public BigDecimal prices(@PathVariable String symbol, @PathVariable String referenceCurrency) {
        // TODO better to delegate to a service
        MarketService.DailyPrices prices = marketService.prices(symbol);
        BigDecimal onePrice = getOnlyElement(prices.getPrices().values());
        String mic = StringUtils.contains(symbol, ".") ? StringUtils.split(symbol, ".")[1] : "XNAS";
        String priceCurrency = marketService.exchange(mic).getCurrency().getCode();
        ForexService.ForexRate rate = forexService.rates(priceCurrency, referenceCurrency);
        return onePrice.multiply(rate.getValue());
    }
}
