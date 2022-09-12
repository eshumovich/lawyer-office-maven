package com.solvd.lawyers.characteristic;

import com.solvd.lawyers.inheritance.IConfirm;
import com.solvd.lawyers.inheritance.Work;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class ClientCase extends Work implements IConfirm {

    private static final Logger LOGGER = LogManager.getLogger(ClientCase.class);

    private String description;

    public ClientCase(String severity, BigDecimal price, String description) {
        super(severity, price);
        this.description = description;
    }

    @Override
    public void provideWork() {
        LOGGER.info("Resolving of client case costs: " + getPrice());
    }

    public Integer caseWaiting(LocalDate prevDate) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(prevDate, now);
        LOGGER.info("Event waiting for: years: " + period.getYears() + " , months: " + period.getMonths() + ", days: " + period.getDays());
        return period.getYears() * 365 + period.getMonths() * 30 + period.getDays();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Case{" +
                "event ='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientCase that = (ClientCase) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public void confirmCase() {
        if (getSeverity() == "ASAP") {
            LOGGER.info("Confirm the case");
        } else {
            LOGGER.info("Case can wait");
        }
    }
}
