package it.minetti.market;

import it.minetti.market.marketstack.ExchangesResponse.Exchange;
import it.minetti.market.marketstack.SymbolPricesResponse;
import it.minetti.market.model.DailyPrices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MarketService {

    private final RestTemplate restTemplate;
    private final MarketstackProperties marketstackProperties;

    public MarketService(RestTemplate restTemplate,
                         MarketstackProperties marketstackProperties

    ) {
        this.restTemplate = restTemplate;
        this.marketstackProperties = marketstackProperties;
    }

    public DailyPrices getHistoricalRates(String symbol, LocalDate from, LocalDate to) {
        SymbolPricesResponse response = restTemplate.getForObject(marketstackProperties.getEodPricesUrl(), SymbolPricesResponse.class, from, symbol, to);

        if (response == null) {
            return new DailyPrices();
        }

        return map(response);
    }

    public DailyPrices getLatestRate(String symbol) {
        log.info("Serving the latest rate for {}.", symbol);
        SymbolPricesResponse response = restTemplate.getForObject(marketstackProperties.getLatestPriceUrl(), SymbolPricesResponse.class, symbol);

        if (response == null) {
            return new DailyPrices();
        }

        return map(response);
    }

    private DailyPrices map(SymbolPricesResponse externalResponse) {
        Map<LocalDate, BigDecimal> data = externalResponse.getData().stream()
                .collect(Collectors.toMap(
                        r -> r.getDate().atZone(ZoneOffset.UTC).toLocalDate(),
                        SymbolPricesResponse.SymbolPrice::getClose));
        return new DailyPrices(data);
    }

    public Exchange getExchange(String mic) {
        log.info("Serving the data for exchange {}.", mic);
        // TODO review because we returns the same object from the API
        return restTemplate.getForObject(marketstackProperties.getExchangesUrl(), Exchange.class, mic);
    }
}
