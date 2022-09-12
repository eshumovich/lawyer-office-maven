package com.solvd.lawyers.characteristic;

import com.solvd.lawyers.inheritance.ICheckStaff;
import com.solvd.lawyers.inheritance.IIncreaseRating;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class Staff implements ICheckStaff {

    private static final Logger LOGGER = LogManager.getLogger(Staff.class);

    private List<Lawyer<? extends IIncreaseRating>> lawyers;

    public Staff(List<Lawyer<? extends IIncreaseRating>> lawyers) {
        this.lawyers = lawyers;
    }

    public void showAllLawyers(List<Lawyer<? extends IIncreaseRating>> lawyers) {
        lawyers.stream()
                .forEach(lawyer -> LOGGER.info(lawyer.getName() + " has rating " + lawyer.getRating() + "; "));
    }

    public void allLawyersBirthdays(List<Lawyer<? extends IIncreaseRating>> lawyers) {
        lawyers.stream()
                .forEach(lawyer -> LOGGER.info(lawyer.getName() + " was born " + lawyer.getBirth()));
    }

    public void getAvailable(ICheckStaff<Lawyer<? extends IIncreaseRating>> availability) {
        boolean isAvailable = lawyers.stream()
                .allMatch(availability::isLawyersPresent);
        if (isAvailable) {
            LOGGER.info("Available lawyers: present");
        } else {
            LOGGER.info("Available lawyers: none");
        }
    }

    public List<Lawyer<? extends IIncreaseRating>> getLawyers() {
        return lawyers;
    }

    public void setLawyers(List<Lawyer<? extends IIncreaseRating>> lawyers) {
        this.lawyers = lawyers;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "lawyers=" + lawyers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return Objects.equals(lawyers, staff.lawyers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lawyers);
    }

    @Override
    public boolean isLawyersPresent(Object o) {
        return false;
    }
}
