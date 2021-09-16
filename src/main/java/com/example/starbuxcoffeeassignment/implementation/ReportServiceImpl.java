package com.example.starbuxcoffeeassignment.implementation;

import com.example.starbuxcoffeeassignment.exception.ApiRequestException;
import com.example.starbuxcoffeeassignment.model.ToppingUsage;
import com.example.starbuxcoffeeassignment.model.UserAmount;
import com.example.starbuxcoffeeassignment.repository.OrderProductDetailRepository;
import com.example.starbuxcoffeeassignment.repository.OrderRepository;
import com.example.starbuxcoffeeassignment.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import java.util.Comparator;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductDetailRepository orderProductDetailRepository;

    public List<UserAmount> getUserSpendingReportService()  throws Exception{
        try{
            return  orderRepository.findSumAmountOfUsers();
        }catch (Exception e){
            throw new ApiRequestException("There is no transaction yet!" );
        }
    };

    public List<ToppingUsage> getUsageOfToppingsReportService()throws Exception{
        try{
            List<ToppingUsage> lstToppingUsage = orderProductDetailRepository.findMostUsedToppingOfDrinks();

            Map<String, ToppingUsage> result = lstToppingUsage.stream()
                    .collect(Collectors.toMap(ToppingUsage::getProductName, Function.identity(),
                            BinaryOperator.maxBy(Comparator.comparing(ToppingUsage::getCount))));

            List<ToppingUsage> lstToppingUsage2 = new ArrayList<ToppingUsage>(result.values());

            return lstToppingUsage2;
        }catch (Exception e){
            throw new ApiRequestException("There is no transaction yet!" );
        }

    };


}
