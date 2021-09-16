package com.example.starbuxcoffeeassignment.controller;

import com.example.starbuxcoffeeassignment.exception.ApiRequestException;
import com.example.starbuxcoffeeassignment.model.ToppingUsage;
import com.example.starbuxcoffeeassignment.model.UserAmount;
import com.example.starbuxcoffeeassignment.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api( tags = "Reports")
@SecurityRequirement(name = "Authorization")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @ApiOperation(value = "This method is used to find total amounts of per customer")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/report/customer-total-amount")
    private List<UserAmount> customerTotalAmountReport() throws Exception{
        try{
            return reportService.getUserSpendingReportService();
        }catch (Exception e){
            throw new ApiRequestException("There is no transaction yet!" );
        }
    }

    @ApiOperation(value = "This method is used to find the popular topping of each product")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/report/max-topping")
    private List<ToppingUsage> maxToppingReport(){
        try{
            return reportService.getUsageOfToppingsReportService();
        }catch (Exception e){
            throw new ApiRequestException("There is no transaction yet!" );
        }
    }


}
