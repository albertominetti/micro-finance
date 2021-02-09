package it.minetti.forex;

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
public class ForexService {

    @Deprecated
    private final RestTemplate restTemplate; // TODO use the FeignClient
    private final String url;

    public ForexService(RestTemplate restTemplate,
                        @Value("${exchangeratesapi.latest-rate-url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public ForexResponse getLatestRate(String target, String reference) {
        ExternalForexResponse response = restTemplate.getForObject(url, ExternalForexResponse.class, reference, target);

        if (response == null || response.getRates().isEmpty()) {
            throw new RuntimeException("Error in the forex provider.");
        }

        return new ForexResponse(getOnlyElement(response.getRates().values()), response.getDate());
    }

    public ForexResponses getHistoricalRates(String target, String reference, LocalDate from, LocalDate to) {
        throw new NotImplementedException();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExternalForexResponse {
        private Map<String, BigDecimal> rates;
        private LocalDate date;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ForexResponse {
        private BigDecimal rate;
        private LocalDate date;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ForexResponses {
        private List<ForexResponse> rates;
    }

}
