package it.minetti.forex;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ForexServiceIT {

    public static final String URL = "https://api.exchangeratesapi.io/latest?symbols={base}&base={target}";

    @Test
    void getSingleRate() {
        ForexService forexService = new ForexService(new RestTemplate(), URL);

        ForexService.ForexResponse singleRate = forexService.getLatestRate("CHF", "EUR");

        assertThat(singleRate, is(notNullValue()));
        assertThat(singleRate.getRate(), is(greaterThan(ZERO)));
        assertThat(singleRate.getRate(), is(lessThan(ONE)));
    }
}