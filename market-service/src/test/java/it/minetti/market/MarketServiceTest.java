package it.minetti.market;

import it.minetti.market.marketstack.SymbolPricesResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MarketServiceTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    MarketService marketService;

    @Test
    @Disabled
    void getSingleRate() {
        when(restTemplate.getForObject(any(), any(), eq("EUR"), eq("CHF")))
                .thenReturn(new SymbolPricesResponse(singletonList(new SymbolPricesResponse.SymbolPrice(BigDecimal.ONE, Instant.now()))));

        Object iwmt = marketService.getHistoricalRates("IWMT", LocalDate.now(), LocalDate.now());

        assertThat(iwmt, is(notNullValue()));
        // TODO...
    }
}