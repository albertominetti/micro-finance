package it.minetti.common;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Component
public class EurekaFeignHealthIndicator extends AbstractHealthIndicator {

    public static final Pattern NAME_PATTERN = Pattern.compile(", name=(.*), ");

    private final ApplicationContext springContext;
    private final EurekaDiscoveryClient eurekaDiscoveryClient;

    private final List<String> feignClients;

    public EurekaFeignHealthIndicator(ApplicationContext springContext, EurekaDiscoveryClient eurekaDiscoveryClient) {
        this.springContext = springContext;
        this.eurekaDiscoveryClient = eurekaDiscoveryClient;

        feignClients = springContext.getBeansWithAnnotation(FeignClient.class).values().stream()
                .map(n -> {
                    Matcher matcher = NAME_PATTERN.matcher(n.toString());
                    if (matcher.find()) {
                        return matcher.group(1);
                    }
                    throw new IllegalArgumentException("Cannot retrieve the service name for a feign client");
                }).collect(toList());
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        boolean isAllUp = true;
        for (String feignClient : feignClients) {
            boolean isUp = isNotEmpty(eurekaDiscoveryClient.getInstances(feignClient));
            builder.withDetail(feignClient, isUp ? "UP" : "DOWN");
            isAllUp &= isUp;
        }
        builder.status(isAllUp ? "UP" : "DOWN");
    }
}
