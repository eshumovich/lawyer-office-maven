package com.solvd.lawyers.inheritance;

import com.solvd.lawyers.Address;

import java.util.Objects;

public class Building<A> implements Openable {

    private Address address;
    private A access;
    private Floor floor;

    public enum Floor {
        FIRST_FLOOR("cafe"), SECOND_FLOOR("office");

        private final String openDoor;

        Floor(String openDoor){
            this.openDoor = openDoor;
        }

        public String getOpenDoor() {
            return openDoor;
        }
    }

    public Building(Address address) {
        this.address = address;
    }

    @Override
    public String checkOpen() {
        return "door is open";
    }

    @Override
    public String toString() {
        return "Building{" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return Objects.equals(address, building.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public A getAccess() {
        return access;
    }

    public void setAccess(A access) {
        this.access = access;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }
}

