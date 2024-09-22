package com.neoflex.testtask.VacationPay.service;

import com.neoflex.testtask.VacationPay.config.Holiday;
import com.neoflex.testtask.VacationPay.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class VacationPayService {
    private final HolidayRepository holidayRepository;
    public BigDecimal calculateVacationPay(
            BigDecimal averageSalary,
            int vacationDays,
            LocalDate start,
            LocalDate end
    ) {
        if (start != null && end != null) {
            int workingDays = 0;

            for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
                Holiday newHoliday = new Holiday(date.getMonthValue(), date.getDayOfMonth());
                if (!isWeekend(date) && !holidayRepository.getHolidays().contains(newHoliday)) {
                    workingDays++;
                }
            }

            var dailySalary = averageSalary.divide(new BigDecimal("29.3"), MathContext.DECIMAL128);
            return dailySalary.multiply(new BigDecimal(workingDays));
        }
        else {
            var dailySalary = averageSalary.divide(new BigDecimal("29.3"), MathContext.DECIMAL128);
            return dailySalary.multiply(new BigDecimal(vacationDays));
        }
    }

    private static boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7;
    }
}
