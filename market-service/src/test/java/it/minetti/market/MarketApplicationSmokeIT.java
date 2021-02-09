package it.minetti.market;

import com.google.common.collect.Iterables;
import it.minetti.common.EurekaFeignHealthIndicator;
import it.minetti.market.model.DailyPrices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@ActiveProfiles("token") // TODO let's find a way to make it compile on github without exposing the token
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.cloud.discovery.enabled=false")
@MockBean(EurekaFeignHealthIndicator.class)
class MarketApplicationSmokeIT {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void smoke_controller() {
        DailyPrices pricesResponse = restTemplate.getForObject("http://localhost:{port}/prices/daily/{symbol}?from={from}&to={to}",
                DailyPrices.class, port, "VUG", "2021-02-03", "2021-02-05");

        assertThat(pricesResponse, is(notNullValue()));
        Map<LocalDate, BigDecimal> prices = pricesResponse.getPrices();
        assertThat(prices, is(notNullValue()));
        assertThat(prices, hasKey(LocalDate.parse("2021-02-03")));
        assertThat(prices, hasKey(LocalDate.parse("2021-02-04")));
        assertThat(prices, hasKey(LocalDate.parse("2021-02-05")));

        for (BigDecimal value : prices.values()) {
            assertThat(value, greaterThan(new BigDecimal(260)));
            assertThat(value, lessThan(new BigDecimal(266)));
        }

    }


    @Test
    public void smoke_controller_latest() {
        DailyPrices pricesResponse = restTemplate.getForObject("http://localhost:{port}/prices/daily/{symbol}",
                DailyPrices.class, port, "VUG");

        assertThat(pricesResponse, is(notNullValue()));
        Map<LocalDate, BigDecimal> prices = pricesResponse.getPrices();
        assertThat(prices, is(notNullValue()));
        assertThat(prices.size(), is(1));

        BigDecimal value = Iterables.getOnlyElement(prices.values());
        assertThat(value, greaterThan(new BigDecimal(250)));

    }

    @Test
    public void smoke_health() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:{port}/actuator/health", String.class, port);

        assertThat(entity, is(notNullValue()));
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
    }

}