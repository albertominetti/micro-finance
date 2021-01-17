package it.minetti.common;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaClient
@ComponentScan("it.minetti.common")
public class CommonConfig {


}