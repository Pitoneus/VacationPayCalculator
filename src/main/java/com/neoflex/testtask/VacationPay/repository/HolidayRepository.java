package com.neoflex.testtask.VacationPay.repository;

import com.neoflex.testtask.VacationPay.config.AppProperties;
import com.neoflex.testtask.VacationPay.config.Holiday;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class HolidayRepository {
    private final AppProperties appProperties;

    public List<Holiday> getHolidays() {
        return List.copyOf(appProperties.getHolidays());
    }
}
