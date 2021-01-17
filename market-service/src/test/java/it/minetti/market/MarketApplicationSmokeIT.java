package it.minetti.market;

import it.minetti.common.EurekaFeignHealthIndicator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.cloud.discovery.enabled=false")
@MockBean(EurekaFeignHealthIndicator.class)
class MarketApplicationSmokeIT {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @Disabled
    public void smoke_controller() {
        Object something = restTemplate.getForObject("http://localhost:{port}/prices/{symbol}?from={from}&to={to}",
                String.class, port, "DND", "2020-01-01", "2020-01-02");

        assertThat(something, is(notNullValue())); // TODO
    }

    @Test
    public void smoke_health() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:{port}/actuator/health", String.class, port);

        assertThat(entity, is(notNullValue()));
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
    }

}