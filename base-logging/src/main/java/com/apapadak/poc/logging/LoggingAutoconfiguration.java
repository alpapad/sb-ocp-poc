package com.apapadak.poc.logging;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration //@AutoConfiguration in 2.7
@EnableConfigurationProperties(FluentdLogginProperties.class)
public class LoggingAutoconfiguration {

}
