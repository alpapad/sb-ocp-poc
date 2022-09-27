package com.apapadak.poc.service;


import org.springframework.stereotype.Service;

import com.apapadak.poc.grpc.EmployeeRequest;
import com.apapadak.poc.grpc.EmployeeResponse;
import com.apapadak.poc.grpc.EmployeeServiceGrpc.EmployeeServiceBlockingStub;

import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class GrpcClientService {

    @GrpcClient("employeeService")
    private EmployeeServiceBlockingStub employeeService;

    public EmployeeResponse getEmployeeRecord(String employeeId) {
        EmployeeRequest request = EmployeeRequest.newBuilder()
                .setEmpId(employeeId).build();
        

        return employeeService.getEmployee(request);
    }
}
