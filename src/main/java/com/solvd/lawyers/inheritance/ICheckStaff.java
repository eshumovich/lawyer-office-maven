package com.solvd.lawyers.inheritance;

@FunctionalInterface
public interface ICheckStaff<L> {

    boolean isLawyersPresent(L l);
}
