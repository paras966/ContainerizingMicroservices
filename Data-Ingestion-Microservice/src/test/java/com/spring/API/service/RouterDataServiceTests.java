package com.spring.API.service;

import com.spring.API.repository.RouterDataOperation;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {RouterDataServiceTests.class})
public class RouterDataServiceTests {
    @Mock
    RouterDataOperation routerDataOperation;

    @InjectMocks
    RouterDataService routerDataService;


}