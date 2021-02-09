package it.minetti.forex;

import it.minetti.forex.ForexService.ExternalForexResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForexServiceTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    ForexService forexService;

    @Test
    void getSingleRate() {
        when(restTemplate.getForObject(any(), any(), eq("CHF"), eq("EUR")))
                .thenReturn(new ExternalForexResponse(Map.of("EUR", new BigDecimal("0.92")), LocalDate.now()));

        ForexService.ForexResponse singleRate = forexService.getLatestRate("EUR", "CHF");

        assertThat(singleRate, is(notNullValue()));
        assertThat(singleRate.getRate(), is(new BigDecimal("0.92")));
    }
}