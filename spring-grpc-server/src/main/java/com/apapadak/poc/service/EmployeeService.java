package com.apapadak.poc.service;

import com.techgeeknext.constants.Role;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apapadak.poc.grpc.EmployeeRequest;
import com.apapadak.poc.grpc.EmployeeResponse;
import com.apapadak.poc.grpc.EmployeeServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class EmployeeService extends EmployeeServiceGrpc.EmployeeServiceImplBase {
    private static Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    /**
     * Unary operation to get the employee based on employee id
     * @param request
     * @param responseObserver
     */
    @Override
    public void getEmployee(EmployeeRequest request,
                            StreamObserver<EmployeeResponse> responseObserver) {

        logger.info("Got request for empid: {}", request.getEmpId());
        //call repository to load the data from database
        //we have added static data for example
        EmployeeResponse empResp = EmployeeResponse
                .newBuilder()
                .setEmpId(request.getEmpId())
                .setName("Name, " + UUID.randomUUID().toString())
                .setRole(Role.USER)
                .build();

        //set the response object
        responseObserver.onNext(empResp);

        //mark process is completed
        responseObserver.onCompleted();
        
    }
}
