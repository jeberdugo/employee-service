package co.parameta.employee.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Value class representing a time period
 * in years, months and days.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimePeriod {
    
    private long years;
    private long months;
    private long days;

    /**
     * Calculates the time period between two dates.
     * 
     * @param startDate start date
     * @param endDate end date
     * @return TimePeriod with the elapsed years, months and days
     */
    public static TimePeriod calculatePeriod(java.time.LocalDate startDate, java.time.LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return TimePeriod.builder()
                    .years(0)
                    .months(0)
                    .days(0)
                    .build();
        }

        if (startDate.isAfter(endDate)) {
            return TimePeriod.builder()
                    .years(0)
                    .months(0)
                    .days(0)
                    .build();
        }

        long years = java.time.temporal.ChronoUnit.YEARS.between(startDate, endDate);
        java.time.LocalDate dateAfterYears = startDate.plusYears(years);
        long months = java.time.temporal.ChronoUnit.MONTHS.between(dateAfterYears, endDate);
        java.time.LocalDate dateAfterMonths = dateAfterYears.plusMonths(months);
        long days = java.time.temporal.ChronoUnit.DAYS.between(dateAfterMonths, endDate);

        return TimePeriod.builder()
                .years(years)
                .months(months)
                .days(days)
                .build();
    }
}

