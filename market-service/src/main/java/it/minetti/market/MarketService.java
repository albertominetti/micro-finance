package it.minetti.market;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Iterables.getOnlyElement;

@Service
public class MarketService {

    private final RestTemplate restTemplate;
    private final String url;

    public MarketService(RestTemplate restTemplate,
                         @Value("${marketstack.eod-prices-url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public Object getHistoricalRates(String symbol, LocalDate from, LocalDate to) {
        throw new NotImplementedException();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExternalResponse {
        private List<SymbolPrice> data;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SymbolPrice {
        private BigDecimal close;
        private LocalDate date;
    }
}
