package com.spring.API.service;

import com.spring.API.dao.RoutersData;
import com.spring.API.repository.RouterDataOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RouterDataService {
    @Autowired
    RouterDataOperation routerDataOperation;
    public List<RoutersData> getAll(){
        return routerDataOperation.getAllData();
    }

    public List<Object> getDeviceData(String deviceId){
        return routerDataOperation.getAllDeviceData(deviceId);
    }

    public List<String> getNts(){
        return routerDataOperation.getAllNts();
    }

    public List<Object> getResources(){
        return routerDataOperation.getAllResources();
    }
}
