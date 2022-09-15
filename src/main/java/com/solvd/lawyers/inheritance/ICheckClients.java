package com.solvd.lawyers.inheritance;

@FunctionalInterface
public interface ICheckClients<C> {

    boolean isClientArrived(C c);
}
