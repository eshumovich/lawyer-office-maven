package com.solvd.lawyers;

import com.solvd.lawyers.characteristic.Client;
import com.solvd.lawyers.characteristic.Lawyer;
import com.solvd.lawyers.characteristic.Service;
import com.solvd.lawyers.characteristic.Staff;
import com.solvd.lawyers.inheritance.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class LawyerOffice extends Organization implements Printable {

    private static final Logger LOGGER = LogManager.getLogger(LawyerOffice.class);

    private Address address;
    private Staff staff;
    private List<Service> services;
    private List<Client<? extends IIncreaseRating>> clients;
    private Map<String, Client<? extends IIncreaseRating>> schedule;

    public LawyerOffice(String name, LocalDate dateOfFoundation, Address address, Staff staff, List<Client<? extends IIncreaseRating>> clients, List<Service> services) {
        super(name, dateOfFoundation);
        this.address = address;
        this.staff = staff;
        this.clients = clients;
        this.services = services;
    }

    public LawyerOffice(String name, LocalDate dateOfFoundation) {
        super(name, dateOfFoundation);
    }

    @Override
    public void issueLicense() {
        LOGGER.info(getName() + " issue a license plus certificate for employees");
    }

    public void startCase() {
        LOGGER.info("Case waiting");
    }

    public void startCase(boolean lawyerAvailable) {
        LOGGER.info("Case waiting");
        if (lawyerAvailable) {
            LOGGER.info("Start case");
        }
    }

    public AtomicInteger countClients() {
        AtomicInteger counter = new AtomicInteger(0);

        clients.stream()
                .forEach(client -> {
                    LOGGER.info("Client: " + client.getName() + " " + client.getClientCase());
                    counter.getAndIncrement();
                });
        return counter;
    }

    public void showServices() {
        services.stream()
                .forEach(service -> LOGGER.info("Show service: " + service.getDescriptionOfService()));
    }

    @Override
    public void print() {
        System.out.printf("%s is founded in %s. The address is: %s \n", getName(), getDateOfFoundation(), getAddress());
    }

    @Override
    public void read() {
    }

    @Override
    public String toString() {
        return "LawyerOffice{" +
                "address=" + address +
                ", staff=" + staff +
                ", services=" + services +
                ", clients=" + clients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LawyerOffice that = (LawyerOffice) o;
        return Objects.equals(address, that.address) && Objects.equals(staff, that.staff) && Objects.equals(services, that.services) && Objects.equals(clients, that.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, staff, services, clients);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Client<? extends IIncreaseRating>> getClients() {
        return clients;
    }

    public void setClients(List<Client<? extends IIncreaseRating>> clients) {
        this.clients = clients;
    }

    public Map<String, Client<? extends IIncreaseRating>> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<String, Client<? extends IIncreaseRating>> schedule) {
        this.schedule = schedule;
    }

    public void meetClient(ICheckClients<Client<? extends IIncreaseRating>> client) {
        boolean isClient = clients.stream()
                .allMatch(client::isClientArrived);
        if (isClient) {
            LOGGER.info("Client will be in 10 minutes");
        } else {
            LOGGER.info("Meeting is canceled");
        }
    }
}

