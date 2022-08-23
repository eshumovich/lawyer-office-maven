package com.solvd.lawyers.inheritance;

public enum ProcessName implements Openable {
    CRIMINAL_CASE("criminal-case"),
    ADMINISTRATIVE_CASE("administrative-case");

    private final String name;

    ProcessName(String name) {
        this.name = name;
    }

    @Override
    public String checkOpen() {
        return name + " description";
    }

    public String getName() {
        return name;
    }
}
