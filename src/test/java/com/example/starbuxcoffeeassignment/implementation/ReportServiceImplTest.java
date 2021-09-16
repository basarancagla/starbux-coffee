package com.example.starbuxcoffeeassignment.implementation;

import com.example.starbuxcoffeeassignment.entity.Order;
import com.example.starbuxcoffeeassignment.entity.User;
import com.example.starbuxcoffeeassignment.model.ToppingUsage;
import com.example.starbuxcoffeeassignment.model.UserAmount;
import com.example.starbuxcoffeeassignment.repository.OrderProductDetailRepository;
import com.example.starbuxcoffeeassignment.repository.OrderRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReportServiceImplTest {
    @InjectMocks
    ReportServiceImpl reportServiceImpl;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderProductDetailRepository orderProductDetailRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserSpendingReportService() {

        List<UserAmount> lstUserAmountInit = Arrays.asList(new UserAmount("Cagla",3.0));
        when(orderRepository.findSumAmountOfUsers()).thenReturn(lstUserAmountInit);
        List<UserAmount> lstUserAmount = reportServiceImpl.getUserSpendingReportService();

        assertEquals(Double.valueOf(3.0),(Double) lstUserAmount.get(0).getTotalAmount());

    }

    @Test
    void getUsageOfToppingsReportService() {


        List<ToppingUsage> lstToppingUsageInit = Arrays.asList(new ToppingUsage("Tea","Milk", 1L),new ToppingUsage("Tea","Lemon", 2L));
        when(orderProductDetailRepository.findMostUsedToppingOfDrinks()).thenReturn(lstToppingUsageInit);

        List<ToppingUsage> lstToppingUsage = reportServiceImpl.getUsageOfToppingsReportService();

        assertEquals(1,lstToppingUsage.size());
        assertEquals("Lemon",lstToppingUsage.get(0).getToppingName());
    }


}