package com.spring.API.dao;

import javax.persistence.*;

@Entity
@Table(name = "routers_table")
public class RoutersData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "time_stamp")
    private String timeStamp;
    @Column(name = "device_id")
    private String deviceId;
    @Column(name = "cpu_utilization")
    private int cpuUtilization;
    @Column(name = "memory_utilization")
    private int memoryUtilization;
    @Column(name = "network_traffic_status")
    private String networkTrafficStatus;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getCpuUtilization() {
        return cpuUtilization;
    }

    public void setCpuUtilization(int cpuUtilization) {
        this.cpuUtilization = cpuUtilization;
    }

    public int getMemoryUtilization() {
        return memoryUtilization;
    }

    public void setMemoryUtilization(int memoryUtilization) {
        this.memoryUtilization = memoryUtilization;
    }

    public String getNetworkTrafficStatus() {
        return networkTrafficStatus;
    }

    public void setNetworkTrafficStatus(String networkTrafficStatus) {
        this.networkTrafficStatus = networkTrafficStatus;
    }
}
