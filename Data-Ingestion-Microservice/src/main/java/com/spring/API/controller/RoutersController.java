package com.spring.API.controller;

import com.spring.API.dao.RoutersData;
import com.spring.API.service.RouterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routers")
public class RoutersController {
    @Autowired
    RouterDataService routerDataService;
    @GetMapping("/all")
    public List<RoutersData> allRouters()
    {
        return routerDataService.getAll();
    }
    @GetMapping("/deviceid")
    public List<Object> deviceData(@RequestParam(value = "deviceid") String deviceId)
    {
        return routerDataService.getDeviceData(deviceId);
    }
    @GetMapping("/networktrafficstatus")
    public List<String> networkRouters()
    {
        return routerDataService.getNts();
    }
    @GetMapping("/resources")
    public List<Object> resources()
    {
        return  routerDataService.getResources();
    }
}
