package it.minetti.forex;

import it.minetti.forex.ForexController.ForexRate;
import it.minetti.common.EurekaFeignHealthIndicator;
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
class ForexApplicationSmokeIT {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void smoke_controller() {
        ForexRate rate = restTemplate.getForObject("http://localhost:{port}/rates/{target}/{reference}",
                ForexRate.class, port, "CHF", "EUR");

        assertThat(rate, is(notNullValue()));
        assertThat(rate.getTarget(), is("CHF"));
        assertThat(rate.getReference(), is("EUR"));
        assertThat(rate.getValue(), is(greaterThan(BigDecimal.ZERO)));
        assertThat(rate.getValue(), is(lessThan(BigDecimal.ONE)));
    }

    @Test
    public void smoke_health() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:{port}/actuator/health", String.class, port);

        assertThat(entity, is(notNullValue()));
        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
    }

}