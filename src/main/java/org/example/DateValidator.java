package org.example;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class DateValidator {

    private final Clock clock;

    public DateValidator(Clock clock) {
        this.clock = clock;
    }

    public boolean isCurrentMonth(Month month) {
        Month currentMonth = LocalDate.now(clock).getMonth();
        return month.equals(currentMonth);
    }

    public boolean isHoliday() {
        DayOfWeek today = LocalDate.now(clock).getDayOfWeek();
        return today.equals(SATURDAY) || today.equals(SUNDAY);
    }
}
