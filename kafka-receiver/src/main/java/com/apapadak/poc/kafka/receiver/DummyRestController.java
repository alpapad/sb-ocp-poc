package com.apapadak.poc.kafka.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apapadak.poc.kafka.payload.KafkaPayload;

@RestController
public class DummyRestController {

    @Autowired
    private KafkaTemplate<String, Object> template;

    @PostMapping(path = "/send/{empId}/{name}")
    public void sendFoo(@PathVariable String empId, String name) {
        this.template.send("topic1", new KafkaPayload(empId, name));
    }
}
