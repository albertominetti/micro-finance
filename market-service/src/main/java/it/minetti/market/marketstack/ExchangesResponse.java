package it.minetti.market.marketstack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangesResponse {
    private List<Exchange> data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Exchange {
        private String name;
        private Currency currency;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Currency {
        private String code;
    }
}
