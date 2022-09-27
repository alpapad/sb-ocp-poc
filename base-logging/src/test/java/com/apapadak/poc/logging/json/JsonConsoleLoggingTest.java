package com.apapadak.poc.logging.json;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = JsonConsoleLoggingTest.DummyApp.class)
@TestPropertySource(properties = {"logging.config=classpath:json-logback.xml","application.name=Test", "spring.info.build.location=classpath:test-build-info.properties"})
public class JsonConsoleLoggingTest {
    Logger logger = LoggerFactory.getLogger(JsonConsoleLoggingTest.class);
    
    @SpringBootApplication
    public static class DummyApp {
    }
    
    @Test
    public void test() {
        logger.error("This is an error", new RuntimeException());
    }
}
