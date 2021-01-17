package it.minetti.market;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MarketServiceIT {

    public static final String URL = "todo....";

    @Test
    @Disabled // TODO
    void getSingleRate() {
        MarketService marketService = new MarketService(new RestTemplate(), URL);

        Object prices = marketService.getHistoricalRates("xxx", LocalDate.now(), LocalDate.now());

        assertThat(prices, is(notNullValue()));
    }
}