package com.solvd.lawyers.characteristic;

import com.solvd.lawyers.exception.InvalidLawyersQuantity;
import com.solvd.lawyers.inheritance.IIncreaseRating;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class Staff {

    private static final Logger LOGGER = LogManager.getLogger(Staff.class);

    private List<Lawyer<? extends IIncreaseRating>> lawyers;

    public Staff(List<Lawyer<? extends IIncreaseRating>> lawyers) {
        this.lawyers = lawyers;
    }

    public void showAllLawyers(List<Lawyer<? extends IIncreaseRating>> lawyers) {
        for (Lawyer<? extends IIncreaseRating> lawyer : lawyers) {
            LOGGER.info(lawyer.getName() + " has rating " + lawyer.getRating() + "; ");
        }
        if (lawyers.size() == 0) {
            throw new InvalidLawyersQuantity("The number of lawyers is to low");
        }
    }

    public void allLawyersBirthdays(List<Lawyer<? extends IIncreaseRating>> lawyers) {
        for (Lawyer<? extends IIncreaseRating> lawyer : lawyers) {
            LOGGER.info(lawyer.getName() + " was born " + lawyer.getBirth());
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
}
