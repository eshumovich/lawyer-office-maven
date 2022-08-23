package com.solvd.lawyers.characteristic;

import com.solvd.lawyers.inheritance.Work;
import com.solvd.lawyers.worktime.VisitTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Objects;

public class Service extends Work {

    private static final Logger LOGGER = LogManager.getLogger(Service.class);

    private VisitTime visitTime;
    private String descriptionOfService;

    public Service(String severity, BigDecimal price, String descriptionOfService) {
        super(severity, price);
        this.descriptionOfService = descriptionOfService;
    }

    public Service(VisitTime visitTime) {
        super();
        this.visitTime = visitTime;
    }

    @Override
    public void provideWork() {
        LOGGER.info("Company can provide additional services. The cost is: " + getPrice());
    }

    @Override
    public String toString() {
        return "Service{" +
                "visit=" + visitTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service1 = (Service) o;
        return Objects.equals(visitTime, service1.visitTime) && Objects.equals(descriptionOfService, service1.descriptionOfService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitTime, descriptionOfService);
    }

    public String getDescriptionOfService() {
        return descriptionOfService;
    }

    public void setDescriptionOfService(String descriptionOfService) {
        this.descriptionOfService = descriptionOfService;
    }

    public VisitTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(VisitTime visitTime) {
        this.visitTime = visitTime;
    }
}
