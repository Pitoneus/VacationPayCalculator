package com.neoflex.testtask.VacationPay.controller;

import com.neoflex.testtask.VacationPay.service.VacationPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/calculate")
public class VacationPayController {
    private final VacationPayService vacationPayService;

    @GetMapping
    public BigDecimal calculateVacationPay(
            BigDecimal averageSalary,
            int vacationDays,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return vacationPayService.calculateVacationPay(averageSalary, vacationDays, startDate, endDate);
    }
}
