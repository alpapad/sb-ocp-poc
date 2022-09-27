package com.apapadak.poc;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.apapadak.poc.grpc.EmployeeResponse;
import com.apapadak.poc.service.GrpcClientService;

@SpringBootApplication
@EnableScheduling
public class GrcpClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrcpClientApplication.class, args);
    }
    
    @Autowired
    private GrpcClientService service;
    
    @Scheduled(fixedRate = 1000)
    public void schedule() {
        EmployeeResponse response = service.getEmployeeRecord(UUID.randomUUID().toString());
        System.err.println(response.getEmpId() + " -> " + response.getName());
    }
}
