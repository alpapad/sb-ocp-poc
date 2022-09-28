package com.apapadak.poc.kafka.payload;

public class KafkaPayload {

    private String empId;
    private String name;

    public KafkaPayload() {
    }

    public KafkaPayload(String empId, String name) {
        super();
        this.empId = empId;
        this.name = name;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "KafkaPayload [empId=" + empId + ", name=" + name + "]";
    }
}
