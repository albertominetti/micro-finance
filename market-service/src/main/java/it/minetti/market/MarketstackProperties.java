package it.minetti.market;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("marketstack")
public class MarketstackProperties {
    private String eodPricesUrl;
    private String latestPriceUrl;
    private String exchangesUrl;
}
