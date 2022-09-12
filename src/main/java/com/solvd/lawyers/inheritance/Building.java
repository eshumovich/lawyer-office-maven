package com.solvd.lawyers.inheritance;

import com.solvd.lawyers.Address;

import java.util.Objects;

public class Building<A> implements Openable {

    private Address address;
    private A access;
    private SchemaBuilding schemaBuilding;

    public enum SchemaBuilding {
        CHECKPOINT("checkpoint"), SECURITY_ROOM("security-room"), COURTROOM("courtroom");

        private final String roomType;

        SchemaBuilding(String openDoor) {
            this.roomType = openDoor;
        }

        public String getRoomType() {
            return roomType;
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
        Building<?> building = (Building<?>) o;
        return Objects.equals(address, building.address) && Objects.equals(access, building.access) && schemaBuilding == building.schemaBuilding;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, access, schemaBuilding);
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

    public SchemaBuilding getSchemaBuilding() {
        return schemaBuilding;
    }

    public void setSchemaBuilding(SchemaBuilding schemaBuilding) {
        this.schemaBuilding = schemaBuilding;
    }
}

