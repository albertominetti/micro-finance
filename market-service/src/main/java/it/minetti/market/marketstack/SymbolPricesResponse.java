package it.minetti.market.marketstack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SymbolPricesResponse {
    private List<SymbolPrice> data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SymbolPrice {
        private BigDecimal close;
        private Instant date;

    }
}
