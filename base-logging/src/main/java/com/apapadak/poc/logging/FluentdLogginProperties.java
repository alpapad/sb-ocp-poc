package com.apapadak.poc.logging;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "logging.fluentd")
public class FluentdLogginProperties {
    private static final String hahaha ="lala"; 
	
    private String host = "localhost";
    private int port = 24224;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
