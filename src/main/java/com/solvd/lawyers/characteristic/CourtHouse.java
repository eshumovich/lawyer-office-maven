package com.solvd.lawyers.characteristic;

import com.solvd.lawyers.Address;
import com.solvd.lawyers.inheritance.AccessableFullDay;
import com.solvd.lawyers.inheritance.Bookable;
import com.solvd.lawyers.inheritance.Building;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class CourtHouse<A extends AccessableFullDay> extends Building<A> implements Bookable {

    private static final Logger LOGGER = LogManager.getLogger(CourtHouse.class);

    private DayOfWeek dayOfWeek;

    public enum DayOfWeek {

        MONDAY("Monday"),
        TUESDAY("Tuesday"),
        WEDNESDAY("Wednesday"),
        THURSDAY("Thursday"),
        FRIDAY("Friday"),
        SATURDAY("Saturday"),
        SUNDAY("Sunday");

        private final String title;

        DayOfWeek(String title){
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    public CourtHouse(Address address) {
        super(address);
    }

    public void bookCourtHouse(LocalDateTime dateTime) {
        LOGGER.info("CourtHouse is booked on: " + dateTime);
    }

    @Override
    public void startTrial() {
        LOGGER.info("Trial is started");
    }

    @Override
    public String toString() {
        return "CourtHouse{}";
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
