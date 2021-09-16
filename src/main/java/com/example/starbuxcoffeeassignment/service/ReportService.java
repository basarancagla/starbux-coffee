package com.example.starbuxcoffeeassignment.service;

import com.example.starbuxcoffeeassignment.model.ToppingUsage;
import com.example.starbuxcoffeeassignment.model.UserAmount;

import java.util.List;

public interface ReportService {
    List<UserAmount> getUserSpendingReportService() throws Exception;
    List<ToppingUsage> getUsageOfToppingsReportService() throws Exception;
}
