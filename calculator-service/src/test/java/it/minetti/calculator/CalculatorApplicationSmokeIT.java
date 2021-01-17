package it.minetti.calculator;

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
class CalculatorApplicationSmokeIT {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @Disabled // TODO
    public void smoke_controller() {
        BigDecimal rate = restTemplate.getForObject("http://localhost:{port}/prices/{listingId}/{currency}",
                BigDecimal.class, port, "ID-12345", "CHF");
        assertThat(rate, is(notNullValue()));
    }

    @Test
    public void smoke_health() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:{port}/actuator/health", String.class, port);

        assertThat(entity, is(notNullValue()));
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
    }

}