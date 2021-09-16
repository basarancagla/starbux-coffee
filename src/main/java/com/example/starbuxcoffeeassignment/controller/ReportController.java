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
    ReportService reportService;

    @ApiOperation(value = "This method is used to find total amounts of per customer")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/report/customer-total-amount")
    public List<UserAmount> customerTotalAmountReport() throws Exception{
        return reportService.getUserSpendingReportService();
    }

    @ApiOperation(value = "This method is used to find the popular topping of each product")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/report/max-topping")
    public List<ToppingUsage> maxToppingReport()throws Exception{
        return reportService.getUsageOfToppingsReportService();
    }


}
