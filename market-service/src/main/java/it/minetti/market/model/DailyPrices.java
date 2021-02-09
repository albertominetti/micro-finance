package it.minetti.market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static java.util.Collections.emptyMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyPrices {
    private Map<LocalDate, BigDecimal> prices = emptyMap();
}
